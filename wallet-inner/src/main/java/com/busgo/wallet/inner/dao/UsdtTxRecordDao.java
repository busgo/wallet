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
     * 根据查询条件查询用户id列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryUserIdListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询唯一单据列表
     * @param query 查询条件
     * @return
     */
     List<String> querySerialNoListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询类型 1-冲币 2-提币列表
     * @param query 查询条件
     * @return
     */
     List<Integer> queryTypeListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询数量列表
     * @param query 查询条件
     * @return
     */
     List<BigDecimal> queryQuantityListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询转出地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryFromAddressListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询转入地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryToAddressListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询合约地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryContractAddressListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询状态 1-处理中，2-成功 -1-失败 列表
     * @param query 查询条件
     * @return
     */
     List<Integer> queryStatusListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询发生日期 yyyyMMdd列表
     * @param query 查询条件
     * @return
     */
     List<Integer> queryOccurDateListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询交易哈希值列表
     * @param query 查询条件
     * @return
     */
     List<String> queryTxHashListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询重试次数列表
     * @param query 查询条件
     * @return
     */
     List<Integer> queryTimesListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询备注列表
     * @param query 查询条件
     * @return
     */
     List<String> queryRemarkListByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询成交时间戳列表
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
     * 根据查询条件查询用户id总数
     * @param query 查询条件
     * @return
     */
     int queryUserIdCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询唯一单据总数
     * @param query 查询条件
     * @return
     */
     int querySerialNoCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询类型 1-冲币 2-提币总数
     * @param query 查询条件
     * @return
     */
     int queryTypeCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询数量总数
     * @param query 查询条件
     * @return
     */
     int queryQuantityCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询转出地址总数
     * @param query 查询条件
     * @return
     */
     int queryFromAddressCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询转入地址总数
     * @param query 查询条件
     * @return
     */
     int queryToAddressCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询合约地址总数
     * @param query 查询条件
     * @return
     */
     int queryContractAddressCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询状态 1-处理中，2-成功 -1-失败 总数
     * @param query 查询条件
     * @return
     */
     int queryStatusCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询发生日期 yyyyMMdd总数
     * @param query 查询条件
     * @return
     */
     int queryOccurDateCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询交易哈希值总数
     * @param query 查询条件
     * @return
     */
     int queryTxHashCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询重试次数总数
     * @param query 查询条件
     * @return
     */
     int queryTimesCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询备注总数
     * @param query 查询条件
     * @return
     */
     int queryRemarkCountByParam(UsdtTxRecordQuery query);

     /***
     * 根据查询条件查询成交时间戳总数
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
