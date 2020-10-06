package com.busgo.wallet.biz.service.impl;

import com.busgo.wallet.biz.service.Erc20UsdtTxLogService;
import com.busgo.wallet.inner.dao.Erc20UsdtTxLogDao;
import com.busgo.wallet.inner.model.Erc20UsdtTxLog;
import com.busgo.wallet.inner.query.Erc20UsdtTxLogQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author busgo
 * @date 2020-10-06 20:20
 **/
@Service
@Slf4j
public class Erc20UsdtTxLogServiceImpl implements Erc20UsdtTxLogService {


    @Autowired
    private Erc20UsdtTxLogDao erc20UsdtTxLogDao;


    @Override
    public void storeTxLog(Erc20UsdtTxLog txLog) {


        if (this.hasExistRecordByTxHash(txLog.getTxHash())) {
            log.warn("当前usdt交易记录已存在 log:{}", txLog);
            return;
        }


        txLog.setCreateTime(new Date());
        this.erc20UsdtTxLogDao.insert(txLog);
        log.info("[存储usdt交易日志] 存储usdt交易记录成功 tx_log:{}", txLog);
    }


    /**
     * 根据交易哈希判断usdt 交易记录是否存在
     *
     * @param txHash 交易哈希
     * @return
     */
    private boolean hasExistRecordByTxHash(String txHash) {

        Erc20UsdtTxLogQuery query = new Erc20UsdtTxLogQuery();
        query.setTxHash(txHash);
        return this.erc20UsdtTxLogDao.queryCountByParam(query) > 0;

    }
}
