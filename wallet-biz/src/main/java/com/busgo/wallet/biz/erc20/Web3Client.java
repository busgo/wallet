package com.busgo.wallet.biz.erc20;

import com.alibaba.fastjson.JSON;
import com.busgo.commons.util.KeyGeneratorUtils;
import com.busgo.wallet.biz.service.Erc20UsdtTxLogService;
import com.busgo.wallet.biz.service.UsdtTxRecordService;
import com.busgo.wallet.commons.constant.Erc20UsdtTxLogStatus;
import com.busgo.wallet.commons.constant.UsdtTxRecordType;
import com.busgo.wallet.commons.exception.WalletBizException;
import com.busgo.wallet.inner.dao.UserWalletDao;
import com.busgo.wallet.inner.model.Erc20UsdtTxLog;
import com.busgo.wallet.inner.model.UserWallet;
import com.busgo.wallet.inner.query.UserWalletQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author busgo
 * @date 2020-10-05 11:56
 **/
@Component
@Slf4j
public class Web3Client implements InitializingBean {

    @Autowired
    private UserWalletDao userWalletDao;

    private Web3j web3j;

    private Admin admin;

    @Value("${erc20.usdt.contract.address}")
    private String contractAddress;

    @Value("${web3j.rpc.url}")
    private String rpcUrl;

    private String walletAddress;

    @Value("${erc20.usdt.wallet.privateKey.password}")
    private String usdtWalletPrivateKeyPassword;

    @Value("${erc20.usdt.wallet.privateKey.path}")
    private String usdtWalletPrivateKeyPath;

    private Credentials credentials;


    @Autowired
    private UsdtTxRecordService usdtTxRecordService;

    @Override
    public void afterPropertiesSet() throws Exception {

        HttpService httpService = new HttpService(this.rpcUrl);
        this.web3j = Web3j.build(httpService);
        this.admin = Admin.build(httpService);
        //加载转账所需的凭证，用私钥
        this.credentials = WalletUtils.loadCredentials(this.usdtWalletPrivateKeyPassword, this.usdtWalletPrivateKeyPath);
        this.walletAddress = this.credentials.getAddress();
        this.subscribe();


        //  this.transferERC20USDT("0xB02a7e1b037Fbad48Ea3E75aE9292E048f084884", "0x9794Db737Aad50A82287781ed7e06CCB03F3064C", new BigDecimal("1.001"));
    }


    /**
     * 添加订阅
     *
     * @throws IOException
     */
    public void subscribe() throws IOException {


        BigInteger currentBlockNumber = this.getCurrentBlockNumber();

        BigInteger startBlockNumber = currentBlockNumber.subtract(BigInteger.valueOf(10000));


        /**
         * 监听ERC20 token 交易
         */

        DefaultBlockParameter from = DefaultBlockParameter.valueOf(startBlockNumber);
        EthFilter filter = new EthFilter(
                from,
                DefaultBlockParameterName.LATEST,
                contractAddress);
        Event event = new Event("Transfer",
                Arrays.<TypeReference<?>>asList(
                        new TypeReference<Address>(true) {
                        },
                        new TypeReference<Address>(true) {
                        }, new TypeReference<Uint256>(false) {
                        }
                )
        );

        String topicData = EventEncoder.encode(event);
        filter.addSingleTopic(topicData);
        System.out.println(topicData);

        this.web3j.ethLogObservable(filter).subscribe(tx -> {


            try {

                EthBlock.Block block = this.getBlockByNumber(tx.getBlockNumber());
                long timestamp = block.getTimestamp().longValue();

                BigDecimal amount = new BigDecimal(new BigInteger(tx.getData().substring(2), 16)).divide(BigDecimal.valueOf(1000000000000000000.0), 18, BigDecimal.ROUND_HALF_EVEN);
                List<String> topics = tx.getTopics();
                String fromAddress = "0x" + topics.get(1).substring(26);
                String toAddress = "0x" + topics.get(2).substring(26);

                Integer type = this.getTxType(fromAddress, toAddress);

                Erc20UsdtTxLog txLog = Erc20UsdtTxLog.builder()
                        .blockNumber(tx.getBlockNumber().longValue())
                        .quantity(amount)
                        .contractAddress(contractAddress)
                        .from(fromAddress)
                        .status(Erc20UsdtTxLogStatus.Success)
                        .type(type)
                        .timestamp(timestamp)
                        .to(toAddress)
                        .txHash(tx.getTransactionHash())
                        .build();
                log.info("接收到 USDT 对应合约地址:{},交易记录:{}", this.contractAddress, txLog);
                this.usdtTxRecordService.dealTxLog(txLog,type);
            } catch (Exception e) {
                log.error(" tx:{}" + JSON.toJSONString(tx), e);
            }
        });


        //  Transfer.sendFunds(this.web3j,this.credentials,)
        log.info("订阅ERC20-USDT交易服务成功 ----> rpc_url:{},contractAddress:{}", this.rpcUrl, this.contractAddress);
    }


    /**
     * 获取转入转出方向
     *
     * @param fromAddress 转出地址
     * @param toAddress   转入地址
     * @return
     */
    private Integer getTxType(String fromAddress, String toAddress) {

        UserWalletQuery query = new UserWalletQuery();
        query.setAddress(toAddress);
        query.setOffset(0);
        query.setRows(1);
        List<UserWallet> wallets = this.userWalletDao.queryListByParam(query);
        if (CollectionUtils.isEmpty(wallets)) {
            if (fromAddress.contains(this.walletAddress)) {
                return UsdtTxRecordType.Withdraw;
            } else {
                return UsdtTxRecordType.None;
            }
        }

        return UsdtTxRecordType.ReChange;
    }


    /**
     * 获取当前区块
     *
     * @return
     * @throws IOException
     */
    private BigInteger getCurrentBlockNumber() throws IOException {

        EthBlockNumber send = this.web3j.ethBlockNumber().send();
        return send.getBlockNumber();


    }

    /**
     * 根据区块号获取区块
     *
     * @param blockNumber 区块号
     * @return
     * @throws IOException
     */
    private EthBlock.Block getBlockByNumber(BigInteger blockNumber) throws IOException {

        EthBlock ethBlock = this.web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), false).send();
        return ethBlock.getBlock();
    }


    /**
     * 创建新钱包
     *
     * @return 钱包地址
     * @throws IOException
     */
    public String createAccount(String password) throws IOException {

        return this.admin.personalNewAccount(password).send().getAccountId();

    }

    /**
     * 解锁钱包
     *
     * @param address  钱包地址
     * @param password 钱包密码
     * @return
     */
    public boolean unlockAccount(String address, String password) throws IOException {
        return this.admin.personalUnlockAccount(address, password).send().accountUnlocked();
    }


    /**
     * 指定地址转出到指定地址
     *
     * @param from     转出地址
     * @param to       转入地址
     * @param quantity 转出数量
     * @return
     */
    public String transferERC20USDT(String from, String to, BigDecimal quantity) {


        log.info("开始转账 ---->from:{},to:{},quantity:{}", from, to, quantity);
        BigInteger amount = Convert.toWei(quantity, Convert.Unit.ETHER).toBigInteger();
        log.info("开始转账[单位换算后] ---->from:{},to:{},amount:{}", from, to, amount);
        //获取nonce，交易笔数
        BigInteger nonce = getNonce(from);
        //get gasPrice
        BigInteger gasPrice = getGasPrice();
        BigInteger gasLimit = Contract.GAS_LIMIT;
        //创建RawTransaction交易对象
        Function function = new Function(
                "transfer",
                Arrays.asList(new Address(to), new Uint256(amount)),
                Collections.singletonList(new TypeReference<Type>() {
                }));

        String encodedFunction = FunctionEncoder.encode(function);


        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit,
                this.contractAddress, encodedFunction);

        //签名Transaction，这里要对交易做签名
        byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, this.credentials);

        String hexValue = Numeric.toHexString(signMessage);

        //发送交易
        String txHash = sendTransaction(hexValue);

        return txHash;
    }


    /**
     * 发送转账请求
     *
     * @param signedTransactionData 交易签名数据
     * @return
     */
    private String sendTransaction(String signedTransactionData) {
        String txHash = StringUtils.EMPTY;
        try {
            EthSendTransaction transaction = web3j.ethSendRawTransaction(signedTransactionData).sendAsync().get();
            if (transaction.hasError()) {
                log.error("Web3j --> ethSendRawTransaction:" + transaction.getError().getMessage());
                return transaction.getTransactionHash();
            }
            log.info("转账成功----> signedTransactionData:{},tx_hash:{}", signedTransactionData, transaction.getTransactionHash());

            return transaction.getTransactionHash();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Web3j --> ethSendRawTransaction:" + e.getMessage());
        }
        return txHash;
    }

    /**
     * 获取  nonce
     *
     * @param from 转出地址
     * @return
     */
    private BigInteger getNonce(String from) {
        try {
            return web3j.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).send().getTransactionCount();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Web3j --> getNonce: from:{}", from, e);
            throw new WalletBizException(-1, "获取手续费价格异常", e);
        }
    }


    /**
     * 获取gas price
     *
     * @return
     */
    private BigInteger getGasPrice() {
        BigInteger gas = null;
        try {
            EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
            if (ethGasPrice != null) gas = ethGasPrice.getGasPrice();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Web3j --> ethGasPrice:" + e.getCause().getMessage(), e);
            throw new WalletBizException(-1, "获取手续费价格异常", e);
        }
        return gas;
    }

}
