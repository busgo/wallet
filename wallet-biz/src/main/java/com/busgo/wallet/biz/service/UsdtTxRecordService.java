package com.busgo.wallet.biz.service;

import com.busgo.wallet.inner.model.UsdtTxRecord;

/**
 * @author busgo
 * @date 2020-10-05 12:09
 **/
public interface UsdtTxRecordService {

    /**
     * 存储usdt 链上交易记录
     * @param record  链上交易记录
     */
    void  storeTxRecord(UsdtTxRecord record);
}
