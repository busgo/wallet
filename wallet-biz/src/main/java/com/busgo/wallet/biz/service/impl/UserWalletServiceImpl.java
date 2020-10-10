package com.busgo.wallet.biz.service.impl;

import com.busgo.commons.constant.wallet.UsdtTxRecordStatus;
import com.busgo.commons.constant.wallet.UsdtTxRecordType;
import com.busgo.commons.util.DateUtils;
import com.busgo.wallet.biz.erc20.Web3JClient;
import com.busgo.wallet.biz.service.UserWalletService;
import com.busgo.wallet.commons.constant.SendTransactionStatus;
import com.busgo.wallet.commons.constant.SymbolCode;
import com.busgo.wallet.commons.exception.WalletBizException;
import com.busgo.wallet.facade.req.TransferUSDTReq;
import com.busgo.wallet.facade.res.TransferUSDTRes;
import com.busgo.wallet.inner.dao.UsdtTxRecordDao;
import com.busgo.wallet.inner.dao.UserWalletDao;
import com.busgo.wallet.inner.model.UsdtTxRecord;
import com.busgo.wallet.inner.model.UserWallet;
import com.busgo.wallet.inner.query.UsdtTxRecordQuery;
import com.busgo.wallet.inner.query.UserWalletQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.web3j.crypto.Hash;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author busgo
 * @date 2020-10-06 17:33
 **/
@Service
@Slf4j
public class UserWalletServiceImpl implements UserWalletService {

    @Autowired
    private UserWalletDao userWalletDao;

    @Autowired
    private Web3JClient web3JClient;

    @Autowired
    private UsdtTxRecordDao usdtTxRecordDao;

    /**
     * 创建钱包账户
     *
     * @param userId 用户id
     * @return
     * @throws IOException
     */
    public UserWallet createWalletAccount(Long userId) throws Exception {

        UserWallet wallet = this.findUserWalletByUserId(userId);
        if (wallet != null) return wallet;

        String password = RandomStringUtils.randomAlphabetic(32);
        String address = this.web3JClient.createAccount(password);
        wallet = UserWallet.builder().address(address).balance(BigDecimal.ZERO)
                .password(password)
                .symbol(SymbolCode.USDT)
                .userId(userId)
                .createTime(new Date())
                .build();

        this.userWalletDao.insert(wallet);
        return wallet;
    }

    /**
     * 根据用户id 查询钱包账户
     *
     * @param userId 用户id
     * @return
     */
    public UserWallet queryWalletAccountByUserId(Long userId) throws Exception {

        UserWallet wallet = this.findUserWalletByUserId(userId);
        if (wallet != null) return wallet;
        return this.createWalletAccount(userId);
    }

    /**
     * 提币
     *
     * @param req 提币申请
     * @return
     */
    @Transactional
    public TransferUSDTRes transfer(TransferUSDTReq req) {


        String serialNo = req.getSerialNo();

        UsdtTxRecord usdtTxRecord = findUsdtTxRecordBySerialNo(serialNo);
        if (usdtTxRecord != null) {
            log.warn("已经提交过提币申请,不需要重复提交 tx_record:{}", usdtTxRecord);
            return TransferUSDTRes.builder()
                    .quantity(req.getQuantity())
                    .serialNo(usdtTxRecord.getSerialNo())
                    .status(usdtTxRecord.getStatus())
                    .txHash(usdtTxRecord.getTxHash())
                    .build();
        }
        String signedTransactionData = this.web3JClient.transfer(req.getAddress(), req.getQuantity());

        String txHash = Hash.sha3(signedTransactionData);
        UsdtTxRecord record = UsdtTxRecord.builder()
                .userId(req.getUserId())
                .type(UsdtTxRecordType.Withdraw)
                .fromAddress(web3JClient.getWalletAddress())
                .toAddress(req.getAddress())
                .contractAddress(web3JClient.getContractAddress())
                .quantity(req.getQuantity())
                .times(0)
                .serialNo(serialNo)
                .status(UsdtTxRecordStatus.No)
                .occurDate(DateUtils.getDateAsInt(new Date()))
                .createTime(new Date())
                .txHash(txHash)
                .build();

        this.usdtTxRecordDao.insert(record);

        Integer sendTransactionStatus = this.web3JClient.sendTransaction(signedTransactionData);
        if (sendTransactionStatus == SendTransactionStatus.Error) {
            log.error("提币请求失败,record:{}", record);
            throw new WalletBizException(-1, "提币失败");
        }

        return TransferUSDTRes.builder()
                .quantity(req.getQuantity())
                .serialNo(record.getSerialNo())
                .status(sendTransactionStatus)
                .txHash(txHash)
                .build();
    }


    public UsdtTxRecord findUsdtTxRecordBySerialNo(String serialNo) {

        UsdtTxRecordQuery query = new UsdtTxRecordQuery();
        query.setSerialNo(serialNo);
        query.setOffset(0);
        query.setRows(1);

        List<UsdtTxRecord> txRecords = this.usdtTxRecordDao.queryListByParam(query);
        if (CollectionUtils.isEmpty(txRecords)) return null;

        return txRecords.get(0);
    }

    /**
     * 根据钱包地址查询钱包详情
     *
     * @param address 钱包地址
     * @return
     */
    public UserWallet queryWalletAccountByAddress(String address) {

        if (StringUtils.isBlank(address)) return null;

        UserWalletQuery query = new UserWalletQuery();
        query.setRows(1);
        query.setOffset(0);
        query.setAddress(address);

        List<UserWallet> wallets = this.userWalletDao.queryListByParam(query);
        if (CollectionUtils.isEmpty(wallets)) return null;

        return wallets.get(0);
    }


    /**
     * 根据用户id 查询钱包账户
     *
     * @param userId 用户id
     * @return
     */
    private UserWallet findUserWalletByUserId(Long userId) {

        UserWalletQuery query = new UserWalletQuery();
        query.setUserId(userId);
        query.setOffset(0);
        query.setRows(1);

        List<UserWallet> userWallets = this.userWalletDao.queryListByParam(query);
        if (CollectionUtils.isEmpty(userWallets)) return null;

        return userWallets.get(0);
    }
}
