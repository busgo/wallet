package com.busgo.wallet.biz.service.impl;

import com.busgo.commons.util.DateUtils;
import com.busgo.commons.util.KeyGeneratorUtils;
import com.busgo.wallet.biz.service.Erc20UsdtTxLogService;
import com.busgo.wallet.biz.service.UsdtTxRecordService;
import com.busgo.wallet.biz.service.UserWalletService;
import com.busgo.wallet.commons.constant.UsdtTxRecordStatus;
import com.busgo.wallet.commons.constant.UsdtTxRecordType;
import com.busgo.wallet.inner.dao.UsdtTxRecordDao;
import com.busgo.wallet.inner.model.Erc20UsdtTxLog;
import com.busgo.wallet.inner.model.UsdtTxRecord;
import com.busgo.wallet.inner.model.UserWallet;
import com.busgo.wallet.inner.query.UsdtTxRecordQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author busgo
 * @date 2020-10-05 12:09
 **/
@Service
@Slf4j
public class UsdtTxRecordServiceImpl implements UsdtTxRecordService {

    @Autowired
    private UsdtTxRecordDao usdtTxRecordDao;

    @Autowired
    private Erc20UsdtTxLogService erc20UsdtTxLogService;

    @Autowired
    private UserWalletService userWalletService;


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

    /**
     * 处理交易记录日志
     *
     * @param txLog usdt 交易记录日志
     * @param type  类型
     * @see UsdtTxRecordType
     */
    @Transactional
    public void dealTxLog(Erc20UsdtTxLog txLog, Integer type) {


        // 存储交易日志
        this.erc20UsdtTxLogService.storeTxLog(txLog);

        if (type == UsdtTxRecordType.Withdraw) {

            log.info("开始处理 USDT 提币交易日志 tx_log:{}", txLog);
            this.dealWithdraw(txLog);

        } else if (type == UsdtTxRecordType.ReChange) {

            log.info("开始处理 USDT 冲币交易日志 tx_log:{}", txLog);
            this.dealRecharge(txLog);

        } else {
            log.warn("不确定对应的交易日志类型 tx_log:{}", txLog);
        }


    }

    @Override
    public int queryCountByParam(UsdtTxRecordQuery query) {
        return this.usdtTxRecordDao.queryCountByParam(query);
    }

    @Override
    public List<UsdtTxRecord> queryListByParam(UsdtTxRecordQuery query) {
        return this.usdtTxRecordDao.queryListByParam(query);
    }

    @Override
    public int storeTxRecord(UsdtTxRecord txRecord) {

        txRecord.setCreateTime(new Date());

        return this.usdtTxRecordDao.insert(txRecord);
    }


    /**
     * 处理提币交易日志
     *
     * @param txLog 交易日志
     */
    private void dealRecharge(Erc20UsdtTxLog txLog) {


        String toAddress = txLog.getTo();

        UserWallet wallet = this.userWalletService.queryWalletAccountByAddress(toAddress);
        if (wallet == null) {
            log.warn("usdt交易日志 tx_log:{},toAddress:{} 钱包信息不存在", txLog, toAddress);
            return;
        }

        String txHash = txLog.getTxHash();
        UsdtTxRecord usdtTxRecord = this.findUSDTTxRecordByTxHash(txHash);
        if (usdtTxRecord != null) {
            log.warn("[处理冲币交易日志] 冲币交易日志 tx_log:{},找到对应的冲币记录单,直接返回", txLog);
            return;
        }


        UsdtTxRecord txRecord = UsdtTxRecord.builder()
                .contractAddress(txLog.getContractAddress())
                .fromAddress(txLog.getFrom())
                .toAddress(toAddress)
                .occurDate(DateUtils.getDateAsInt(new Date()))
                .quantity(txLog.getQuantity())
                .serialNo(KeyGeneratorUtils.generateSerialNo())
                .status(UsdtTxRecordStatus.No)
                .times(0)
                .timestamp(txLog.getTimestamp())
                .txHash(txHash)
                .type(UsdtTxRecordType.ReChange)
                .userId(wallet.getUserId())
                .build();
        this.storeTxRecord(txRecord);
        log.info("[处理冲交易日志] 冲币交易日志 tx_log:{},已生成冲币单:{}", txLog, txRecord);

    }

    /**
     * 处理提币交易日志
     *
     * @param txLog 交易日志
     */
    private void dealWithdraw(Erc20UsdtTxLog txLog) {

        String txHash = txLog.getTxHash();
        UsdtTxRecord usdtTxRecord = this.findUSDTTxRecordByTxHash(txHash);
        if (usdtTxRecord == null) {
            log.warn("[处理提币交易日志] 提币交易日志 tx_log:{},没有找到对应的提币记录单", txLog);
            return;
        } else if (usdtTxRecord.getStatus() != UsdtTxRecordStatus.No) {
            log.warn("[处理提币交易日志] 提币交易日志 tx_log:{},找到对应的提币记录单:{} 不是提币中的状态", txLog, usdtTxRecord);
            return;
        }

        UsdtTxRecord record = new UsdtTxRecord();
        record.setId(usdtTxRecord.getId());
        record.setStatus(UsdtTxRecordStatus.Success);
        record.setModifyTime(new Date());
        this.usdtTxRecordDao.updateById(record);

        log.info("[处理提币交易日志] 提币交易日志 tx_log:{},找到对应的提币记录单:{} 并处理成功", txLog, usdtTxRecord);
    }


    /**
     * 根据交易哈希查询 usdt交易记录
     *
     * @param txHash 交易哈希
     * @return
     */
    private UsdtTxRecord findUSDTTxRecordByTxHash(String txHash) {

        UsdtTxRecordQuery query = new UsdtTxRecordQuery();
        query.setTxHash(txHash);
        query.setOffset(0);
        query.setRows(1);

        List<UsdtTxRecord> txRecords = this.usdtTxRecordDao.queryListByParam(query);
        if (CollectionUtils.isEmpty(txRecords)) return null;

        return txRecords.get(0);
    }
}
