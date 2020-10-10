package com.busgo.wallet.facade;

import com.busgo.commons.lang.Pager;
import com.busgo.commons.lang.Result;
import com.busgo.wallet.facade.req.QueryUsdtTxRecordListReq;
import com.busgo.wallet.facade.res.QueryUsdtTxRecordDetailRes;

/**
 * @author busgo
 * @date 2020-10-07 21:30
 **/
public interface UsdtTxRecordFacade {


    /**
     * 分页查询usdt 交易记录
     *
     * @param req 请求
     * @return
     */
    Result<Pager<QueryUsdtTxRecordDetailRes>> queryUsdtTxRecordList(QueryUsdtTxRecordListReq req);
}
