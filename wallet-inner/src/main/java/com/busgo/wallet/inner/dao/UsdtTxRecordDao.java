package com.busgo.wallet.inner.dao;

import com.busgo.wallet.inner.model.UsdtTxRecord;
import com.busgo.wallet.inner.query.UsdtTxRecordQuery;

import    java.util.Date;
import    java.math.BigDecimal;
import    java.util.List;
/***
 *
 * @author Create By AutoGenerator
 */
public interface UsdtTxRecordDao extends BaseDao<Long,UsdtTxRecord,UsdtTxRecordQuery> {


     /***
     * 根据查询条件查询主键列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryIdListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询币种列表
     * @param query 查询条件
     * @return
     */
     List<String> querySymbolListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询交易单号列表
     * @param query 查询条件
     * @return
     */
     List<String> querySerialNoListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询区块号列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryBlockNumberListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询交易哈希值列表
     * @param query 查询条件
     * @return
     */
     List<String> queryTxHashListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询金额列表
     * @param query 查询条件
     * @return
     */
     List<BigDecimal> queryAmountListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询重试次数列表
     * @param query 查询条件
     * @return
     */
     List<Integer> queryTimesListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询1-冲币 2-提币列表
     * @param query 查询条件
     * @return
     */
     List<Integer> querySideListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询from 钱包地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryFromListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询to 钱包地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryToListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询合约地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryContractAddressListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询状态 1-待处理 2-成功 -1-失败列表
     * @param query 查询条件
     * @return
     */
     List<Integer> queryStatusListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询成交时间列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryTimestampListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询创建时间列表
     * @param query 查询条件
     * @return
     */
     List<Date> queryCreateTimeListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询修改时间列表
     * @param query 查询条件
     * @return
     */
     List<Date> queryModifyTimeListByParam(UsdtTxRecordQuery query);


     /***
     * 根据查询条件查询主键总数
     * @param query 查询条件
     * @return
     */
     int queryIdCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询币种总数
     * @param query 查询条件
     * @return
     */
     int querySymbolCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询交易单号总数
     * @param query 查询条件
     * @return
     */
     int querySerialNoCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询区块号总数
     * @param query 查询条件
     * @return
     */
     int queryBlockNumberCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询交易哈希值总数
     * @param query 查询条件
     * @return
     */
     int queryTxHashCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询金额总数
     * @param query 查询条件
     * @return
     */
     int queryAmountCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询重试次数总数
     * @param query 查询条件
     * @return
     */
     int queryTimesCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询1-冲币 2-提币总数
     * @param query 查询条件
     * @return
     */
     int querySideCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询from 钱包地址总数
     * @param query 查询条件
     * @return
     */
     int queryFromCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询to 钱包地址总数
     * @param query 查询条件
     * @return
     */
     int queryToCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询合约地址总数
     * @param query 查询条件
     * @return
     */
     int queryContractAddressCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询状态 1-待处理 2-成功 -1-失败总数
     * @param query 查询条件
     * @return
     */
     int queryStatusCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询成交时间总数
     * @param query 查询条件
     * @return
     */
     int queryTimestampCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询创建时间总数
     * @param query 查询条件
     * @return
     */
     int queryCreateTimeCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询修改时间总数
     * @param query 查询条件
     * @return
     */
     int queryModifyTimeCountByParam(UsdtTxRecordQuery query);


}
