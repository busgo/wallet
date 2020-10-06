package com.busgo.wallet.inner.model;

import    lombok.AllArgsConstructor;
import    lombok.Builder;
import    lombok.Data;
import    lombok.NoArgsConstructor;

import    java.io.Serializable;

import    java.util.Date;
import    java.math.BigDecimal;
/***
 *
 * @author Create By AutoGenerator
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWallet implements Serializable {



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


}
