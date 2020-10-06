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
public class Erc20UsdtTxLog implements Serializable {



	// 主键
	private Long id;

	// 区块号
	private Long blockNumber;

	// 交易哈希值
	private String txHash;

	// 数量
	private BigDecimal quantity;

	// 1-冲币 2-提币
	private Integer type;

	// from 钱包地址
	private String from;

	// to 钱包地址
	private String to;

	// 合约地址
	private String contractAddress;

	// 状态 1-待处理 2-成功 -1-失败
	private Integer status;

	// 成交时间
	private Long timestamp;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;


}
