package com.busgo.wallet.biz.service;

import com.busgo.wallet.inner.model.Erc20UsdtTxLog;

/**
 * @author busgo
 * @date 2020-10-06 20:19
 **/
public interface Erc20UsdtTxLogService {

    /**
     * 存储usdt 链上交易记录
     * @param txLog  链上交易记录
     */
    void  storeTxLog(Erc20UsdtTxLog txLog);
}
