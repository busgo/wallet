package com.busgo.wallet.inner.dao;

import com.busgo.wallet.inner.model.UserWallet;
import com.busgo.wallet.inner.query.UserWalletQuery;

import    java.util.Date;
import    java.math.BigDecimal;
import    java.util.List;
/***
 *
 * @author Create By AutoGenerator
 */
public interface UserWalletDao extends BaseDao<Long,UserWallet,UserWalletQuery> {


     /***
     * 根据查询条件查询主键列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryIdListByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询user_id列表
     * @param query 查询条件
     * @return
     */
     List<Long> queryUserIdListByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询币种列表
     * @param query 查询条件
     * @return
     */
     List<String> querySymbolListByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询余额列表
     * @param query 查询条件
     * @return
     */
     List<BigDecimal> queryBalanceListByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询钱包密码列表
     * @param query 查询条件
     * @return
     */
     List<String> queryPasswordListByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询钱包地址列表
     * @param query 查询条件
     * @return
     */
     List<String> queryAddressListByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询创建时间列表
     * @param query 查询条件
     * @return
     */
     List<Date> queryCreateTimeListByParam(UserWalletQuery query);


     /***
     * 根据查询条件查询主键总数
     * @param query 查询条件
     * @return
     */
     int queryIdCountByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询user_id总数
     * @param query 查询条件
     * @return
     */
     int queryUserIdCountByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询币种总数
     * @param query 查询条件
     * @return
     */
     int querySymbolCountByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询余额总数
     * @param query 查询条件
     * @return
     */
     int queryBalanceCountByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询钱包密码总数
     * @param query 查询条件
     * @return
     */
     int queryPasswordCountByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询钱包地址总数
     * @param query 查询条件
     * @return
     */
     int queryAddressCountByParam(UserWalletQuery query);

     /***
     * 根据查询条件查询创建时间总数
     * @param query 查询条件
     * @return
     */
     int queryCreateTimeCountByParam(UserWalletQuery query);


}
