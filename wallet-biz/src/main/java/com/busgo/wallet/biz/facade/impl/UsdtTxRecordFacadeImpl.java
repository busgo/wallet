package com.busgo.wallet.biz.facade.impl;

import com.busgo.commons.lang.Pager;
import com.busgo.commons.lang.Result;
import com.busgo.wallet.biz.service.UsdtTxRecordService;
import com.busgo.wallet.facade.UsdtTxRecordFacade;
import com.busgo.wallet.facade.req.QueryUsdtTxRecordListReq;
import com.busgo.wallet.facade.res.QueryUsdtTxRecordDetailRes;
import com.busgo.wallet.inner.model.UsdtTxRecord;
import com.busgo.wallet.inner.query.UsdtTxRecordQuery;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author busgo
 * @date 2020-10-07 21:30
 **/
@DubboService
public class UsdtTxRecordFacadeImpl implements UsdtTxRecordFacade {

    @Autowired
    private UsdtTxRecordService usdtTxRecordService;


    /**
     * 根据条件分页查询 usdt 交易记录
     *
     * @param req 请求
     * @return
     */
    public Result<Pager<QueryUsdtTxRecordDetailRes>> queryUsdtTxRecordList(QueryUsdtTxRecordListReq req) {


        req.checkParam();

        UsdtTxRecordQuery query = new UsdtTxRecordQuery();
        query.setStatus(req.getStatus());
        query.setType(req.getType());

        int count = this.usdtTxRecordService.queryCountByParam(query);
        Pager<QueryUsdtTxRecordDetailRes> pager = new Pager<>(req.getPageNo(), req.getPageSize());

        List<QueryUsdtTxRecordDetailRes> detailRes = Lists.newArrayList();
        if (count > 0) {
            query.setRows(req.getPageSize());
            query.setOffset(Pager.offset(req.getPageNo(), req.getPageSize()));
            List<UsdtTxRecord> txRecords = this.usdtTxRecordService.queryListByParam(query);


            if (!CollectionUtils.isEmpty(txRecords)) {
                txRecords.forEach(record -> {
                    QueryUsdtTxRecordDetailRes res = new QueryUsdtTxRecordDetailRes();

                    BeanUtils.copyProperties(record, res);
                    detailRes.add(res);
                });
            }
        }


        pager.setTotal(count);
        pager.setList(detailRes);
        return Result.buildSuccess(pager);
    }
}
