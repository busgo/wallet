package com.busgo.wallet.inner.dao;

import com.busgo.wallet.inner.model.Erc20UsdtTxLog;
import com.busgo.wallet.inner.query.Erc20UsdtTxLogQuery;

import    java.util.Date;
import    java.math.BigDecimal;
import    java.util.List;
/***
 *
 * @author Create By AutoGenerator
 */
public interface Erc20UsdtTxLogDao extends BaseDao<Long,Erc20UsdtTxLog,Erc20UsdtTxLogQuery> {


     /***
     * 根据查询条件查询主键列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryIdListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询区块号列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryBlockNumberListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询交易哈希值列表
     * @param query 查询条件
     * @return
     */
     List<String> queryTxHashListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询数量列表
     * @param query 查询条件
     * @return
     */
     List<BigDecimal> queryQuantityListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询1-冲币 2-提币列表
     * @param query 查询条件
     * @return
     */
     List<Integer> queryTypeListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询from 钱包地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryFromListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询to 钱包地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryToListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询合约地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryContractAddressListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询状态 1-待处理 2-成功 -1-失败列表
     * @param query 查询条件
     * @return
     */
     List<Integer> queryStatusListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询成交时间列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryTimestampListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询创建时间列表
     * @param query 查询条件
     * @return
     */
     List<Date> queryCreateTimeListByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询修改时间列表
     * @param query 查询条件
     * @return
     */
     List<Date> queryModifyTimeListByParam(Erc20UsdtTxLogQuery query);


     /***
     * 根据查询条件查询主键总数
     * @param query 查询条件
     * @return
     */
     int queryIdCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询区块号总数
     * @param query 查询条件
     * @return
     */
     int queryBlockNumberCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询交易哈希值总数
     * @param query 查询条件
     * @return
     */
     int queryTxHashCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询数量总数
     * @param query 查询条件
     * @return
     */
     int queryQuantityCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询1-冲币 2-提币总数
     * @param query 查询条件
     * @return
     */
     int queryTypeCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询from 钱包地址总数
     * @param query 查询条件
     * @return
     */
     int queryFromCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询to 钱包地址总数
     * @param query 查询条件
     * @return
     */
     int queryToCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询合约地址总数
     * @param query 查询条件
     * @return
     */
     int queryContractAddressCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询状态 1-待处理 2-成功 -1-失败总数
     * @param query 查询条件
     * @return
     */
     int queryStatusCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询成交时间总数
     * @param query 查询条件
     * @return
     */
     int queryTimestampCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询创建时间总数
     * @param query 查询条件
     * @return
     */
     int queryCreateTimeCountByParam(Erc20UsdtTxLogQuery query);

     /***
     * 根据查询条件查询修改时间总数
     * @param query 查询条件
     * @return
     */
     int queryModifyTimeCountByParam(Erc20UsdtTxLogQuery query);


}
