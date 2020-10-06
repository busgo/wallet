package com.busgo.wallet.biz.service;

import com.busgo.wallet.facade.req.TransferUSDTReq;
import com.busgo.wallet.facade.res.TransferUSDTRes;
import com.busgo.wallet.inner.model.UserWallet;

import java.io.IOException;

/**
 * @author busgo
 * @date 2020-10-06 17:33
 **/
public interface UserWalletService {


    /**
     * 创建钱包账户
     *
     * @param userId 用户id
     * @return
     */
    UserWallet createWalletAccount(Long userId) throws IOException;

    /**
     * 根据用户id 查询钱包账户
     *
     * @param userId 用户id
     * @return
     */
    UserWallet queryWalletAccountByUserId(Long userId) throws IOException;

    /**
     * 提币
     *
     * @param req 提币请求
     * @return
     */
    TransferUSDTRes transfer(TransferUSDTReq req);


    /**
     * 根据钱包地址查询钱包信息
     * @param address 钱包地址
     * @return
     */
    UserWallet queryWalletAccountByAddress(String address);


}
