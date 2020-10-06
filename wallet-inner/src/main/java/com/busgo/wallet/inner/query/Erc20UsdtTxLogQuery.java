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
public class Erc20UsdtTxLogQuery extends BaseQuery {


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

	// include 主键
	private List<Long> includeIdList;

    // exclude 主键
    private List<Long> excludeIdList;

	// include 区块号
	private List<Long> includeBlockNumberList;

    // exclude 区块号
    private List<Long> excludeBlockNumberList;

	// include 交易哈希值
	private List<String> includeTxHashList;

    // exclude 交易哈希值
    private List<String> excludeTxHashList;

	// include 数量
	private List<BigDecimal> includeQuantityList;

    // exclude 数量
    private List<BigDecimal> excludeQuantityList;

	// include 1-冲币 2-提币
	private List<Integer> includeTypeList;

    // exclude 1-冲币 2-提币
    private List<Integer> excludeTypeList;

	// include from 钱包地址
	private List<String> includeFromList;

    // exclude from 钱包地址
    private List<String> excludeFromList;

	// include to 钱包地址
	private List<String> includeToList;

    // exclude to 钱包地址
    private List<String> excludeToList;

	// include 合约地址
	private List<String> includeContractAddressList;

    // exclude 合约地址
    private List<String> excludeContractAddressList;

	// include 状态 1-待处理 2-成功 -1-失败
	private List<Integer> includeStatusList;

    // exclude 状态 1-待处理 2-成功 -1-失败
    private List<Integer> excludeStatusList;

	// include 成交时间
	private List<Long> includeTimestampList;

    // exclude 成交时间
    private List<Long> excludeTimestampList;

	// include 创建时间
	private List<Date> includeCreateTimeList;

    // exclude 创建时间
    private List<Date> excludeCreateTimeList;

	// include 修改时间
	private List<Date> includeModifyTimeList;

    // exclude 修改时间
    private List<Date> excludeModifyTimeList;


}
