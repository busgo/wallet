package com.busgo.wallet.biz.service.impl;

import com.busgo.wallet.biz.service.UsdtTxRecordService;
import com.busgo.wallet.inner.dao.UsdtTxRecordDao;
import com.busgo.wallet.inner.model.UsdtTxRecord;
import com.busgo.wallet.inner.query.UsdtTxRecordQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author busgo
 * @date 2020-10-05 12:09
 **/
@Service
@Slf4j
public class UsdtTxRecordServiceImpl implements UsdtTxRecordService {

    @Autowired
    private UsdtTxRecordDao usdtTxRecordDao;

    @Override
    public void storeTxRecord(UsdtTxRecord record) {

        if (this.hasExistRecordByTxHash(record.getTxHash())){
            log.warn("当前usdt交易记录已存在 record:{}",record);
            return;
        }


        this.usdtTxRecordDao.insert(record);
    }


    /**
     * 根据交易哈希判断usdt 交易记录是否存在
     *
     * @param txHash 交易哈希
     * @return
     */
    private boolean hasExistRecordByTxHash(String txHash) {

        UsdtTxRecordQuery query = new UsdtTxRecordQuery();
        query.setTxHash(txHash);
        return this.usdtTxRecordDao.queryCountByParam(query) > 0;

    }
}
