package com.busgo.wallet.inner.query;

import    lombok.Data;


import    java.util.Date;
import    java.math.BigDecimal;
import    java.util.List;
/***
 *
 * @author Create By AutoGenerator
 */
@Data
public class UserWalletQuery extends BaseQuery {


	// 主键
	private Long id;

	// user_id
	private Long userId;

	// 币种
	private String symbol;

	// 余额
	private BigDecimal balance;

	// 钱包密码
	private String password;

	// 钱包地址
	private String address;

	// 创建时间
	private Date createTime;

	// include 主键
	private List<Long> includeIdList;

    // exclude 主键
    private List<Long> excludeIdList;

	// include user_id
	private List<Long> includeUserIdList;

    // exclude user_id
    private List<Long> excludeUserIdList;

	// include 币种
	private List<String> includeSymbolList;

    // exclude 币种
    private List<String> excludeSymbolList;

	// include 余额
	private List<BigDecimal> includeBalanceList;

    // exclude 余额
    private List<BigDecimal> excludeBalanceList;

	// include 钱包密码
	private List<String> includePasswordList;

    // exclude 钱包密码
    private List<String> excludePasswordList;

	// include 钱包地址
	private List<String> includeAddressList;

    // exclude 钱包地址
    private List<String> excludeAddressList;

	// include 创建时间
	private List<Date> includeCreateTimeList;

    // exclude 创建时间
    private List<Date> excludeCreateTimeList;


}
