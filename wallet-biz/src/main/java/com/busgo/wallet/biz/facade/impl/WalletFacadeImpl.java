package com.busgo.wallet.biz.facade.impl;

import com.busgo.commons.lang.Result;
import com.busgo.commons.lang.ResultCode;
import com.busgo.wallet.biz.service.UserWalletService;
import com.busgo.wallet.commons.exception.WalletBizException;
import com.busgo.wallet.facade.WalletFacade;
import com.busgo.wallet.facade.req.CreateWalletAccountReq;
import com.busgo.wallet.facade.req.QueryWalletAccountReq;
import com.busgo.wallet.facade.req.TransferUSDTReq;
import com.busgo.wallet.facade.res.CreateWalletAccountRes;
import com.busgo.wallet.facade.res.QueryWalletAccountRes;
import com.busgo.wallet.facade.res.TransferUSDTRes;
import com.busgo.wallet.inner.model.UserWallet;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 钱包服务 facade 实现
 *
 * @author busgo
 * @date 2020-10-06 17:22
 **/
@DubboService
@Slf4j
public class WalletFacadeImpl implements WalletFacade {

    @Autowired
    private UserWalletService userWalletService;

    @Override
    public Result<CreateWalletAccountRes> createWalletAccount(CreateWalletAccountReq req) {

        try {

            req.checkParam();
            UserWallet wallet = this.userWalletService.createWalletAccount(req.getUserId());
            return Result.buildSuccess(CreateWalletAccountRes.builder().address(wallet.getAddress()).build());
        } catch (WalletBizException e) {
            log.error("创建钱包账户异常:{}", e.getMessage(), e);
            return Result.buildFail(e.getCode(), e.getMessage());
        } catch (IllegalArgumentException e2) {
            log.error("创建钱包账户异常:{}", e2.getMessage(), e2);
            return Result.buildFail(ResultCode.ParamErrorCode, e2.getMessage());
        } catch (Throwable e1) {
            log.error("创建钱包账户异常:{}", e1.getMessage(), e1);
            return Result.buildBusinessFail(e1.getMessage());
        }
    }

    @Override
    public Result<QueryWalletAccountRes> queryWalletAccountByUserId(QueryWalletAccountReq req) {
        try {

            req.checkParam();
            UserWallet wallet = this.userWalletService.queryWalletAccountByUserId(req.getUserId());
            return Result.buildSuccess(QueryWalletAccountRes.builder().address(wallet.getAddress()).build());
        } catch (WalletBizException e) {
            log.error("查询钱包异常:{}", e.getMessage(), e);
            return Result.buildFail(e.getCode(), e.getMessage());
        } catch (IllegalArgumentException e2) {
            log.error("查询钱包异常:{}", e2.getMessage(), e2);
            return Result.buildFail(ResultCode.ParamErrorCode, e2.getMessage());
        } catch (Throwable e1) {
            log.error("查询钱包异常:{}", e1.getMessage(), e1);
            return Result.buildBusinessFail(e1.getMessage());
        }
    }

    /**
     * 提币
     *
     * @param req 请求
     * @return
     */
    public Result<TransferUSDTRes> transfer(TransferUSDTReq req) {

        try {

            req.checkParam();
           TransferUSDTRes transferUSDTRes = this.userWalletService.transfer(req);
            return Result.buildSuccess(transferUSDTRes);
        } catch (WalletBizException e) {
            log.error("提币请求异常:{}", e.getMessage(), e);
            return Result.buildFail(e.getCode(), e.getMessage());
        } catch (IllegalArgumentException e2) {
            log.error("提币请求异常:{}", e2.getMessage(), e2);
            return Result.buildFail(ResultCode.ParamErrorCode, e2.getMessage());
        } catch (Throwable e1) {
            log.error("提币请求异常:{}", e1.getMessage(), e1);
            return Result.buildBusinessFail(e1.getMessage());
        }
    }
}
