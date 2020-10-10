package com.busgo.wallet.biz.service;

import com.busgo.wallet.inner.model.Erc20UsdtTxLog;
import com.busgo.wallet.inner.model.UsdtTxRecord;
import com.busgo.wallet.inner.query.UsdtTxRecordQuery;

import java.util.List;

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

    /**
     * 根据条件查询 usdt 交易记录
     *
     * @param query 查询条件
     * @return
     */
    int queryCountByParam(UsdtTxRecordQuery query);

    /**
     * 根据条件分页查询 usdt 交易记录列表
     *
     * @param query 查询条件
     * @return
     */
    List<UsdtTxRecord> queryListByParam(UsdtTxRecordQuery query);


    /**
     * 存储 usdt 交易记录
     *
     * @param txRecord usdt 交易记录
     * @return
     */
    int storeTxRecord(UsdtTxRecord txRecord);


}
