package com.busgo.wallet.facade;

import com.busgo.commons.lang.Result;
import com.busgo.wallet.facade.req.CreateWalletAccountReq;
import com.busgo.wallet.facade.req.QueryWalletAccountReq;
import com.busgo.wallet.facade.req.TransferUSDTReq;
import com.busgo.wallet.facade.res.CreateWalletAccountRes;
import com.busgo.wallet.facade.res.QueryWalletAccountRes;
import com.busgo.wallet.facade.res.TransferUSDTRes;

/**
 * 钱包服务 facade
 *
 * @author busgo
 * @date 2020-10-06 17:21
 **/
public interface WalletFacade {


    /**
     * 创建钱包
     *
     * @param req 请求
     * @return
     */
    Result<CreateWalletAccountRes> createWalletAccount(CreateWalletAccountReq req);

    /**
     * 根据用户id 查询钱包账户
     *
     * @param req 请求
     * @return
     */
    Result<QueryWalletAccountRes> queryWalletAccountByUserId(QueryWalletAccountReq req);


    /**
     * 提币申请
     *
     * @param req 请求
     * @return
     */
    Result<TransferUSDTRes> transfer(TransferUSDTReq req);


}
