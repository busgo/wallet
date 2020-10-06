package com.busgo.wallet.biz.service;

import com.busgo.wallet.inner.model.Erc20UsdtTxLog;

/**
 * @author busgo
 * @date 2020-10-05 12:09
 **/
public interface UsdtTxRecordService {
    
    /**
     * 处理usdt 链上交易记录
     *
     * @param txLog 链上交易记录
     * @param type  类型
     * @see com.busgo.wallet.commons.constant.UsdtTxRecordType
     */
    void dealTxLog(Erc20UsdtTxLog txLog, Integer type);
}
