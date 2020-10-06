package com.busgo.wallet.biz.service.impl;

import com.busgo.wallet.biz.erc20.Web3Client;
import com.busgo.wallet.biz.service.UserWalletService;
import com.busgo.wallet.commons.constant.SymbolCode;
import com.busgo.wallet.facade.req.TransferUSDTReq;
import com.busgo.wallet.facade.res.TransferUSDTRes;
import com.busgo.wallet.inner.dao.UserWalletDao;
import com.busgo.wallet.inner.model.UserWallet;
import com.busgo.wallet.inner.query.UserWalletQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private Web3Client web3Client;

    /**
     * 创建钱包账户
     *
     * @param userId 用户id
     * @return
     * @throws IOException
     */
    public UserWallet createWalletAccount(Long userId) throws IOException {

        UserWallet wallet = this.findUserWalletByUserId(userId);
        if (wallet != null) return wallet;

        String password = RandomStringUtils.randomAlphabetic(32);
        String address = this.web3Client.createAccount(password);
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
    public UserWallet queryWalletAccountByUserId(Long userId) throws IOException {

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


        return null;
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
