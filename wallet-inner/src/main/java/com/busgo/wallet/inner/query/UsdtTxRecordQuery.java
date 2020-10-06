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
public class UsdtTxRecordQuery extends BaseQuery {


	// 主键
	private Long id;

	// 用户id
	private Long userId;

	// 唯一单据
	private String serialNo;

	// 类型 1-冲币 2-提币
	private Integer type;

	// 数量
	private BigDecimal quantity;

	// 转出地址
	private String fromAddress;

	// 转入地址
	private String toAddress;

	// 合约地址
	private String contractAddress;

	// 状态 1-处理中，2-成功 -1-失败 
	private Integer status;

	// 发生日期 yyyyMMdd
	private Integer occurDate;

	// 交易哈希值
	private String txHash;

	// 重试次数
	private Integer times;

	// 备注
	private String remark;

	// 成交时间戳
	private Long timestamp;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;

	// include 主键
	private List<Long> includeIdList;

    // exclude 主键
    private List<Long> excludeIdList;

	// include 用户id
	private List<Long> includeUserIdList;

    // exclude 用户id
    private List<Long> excludeUserIdList;

	// include 唯一单据
	private List<String> includeSerialNoList;

    // exclude 唯一单据
    private List<String> excludeSerialNoList;

	// include 类型 1-冲币 2-提币
	private List<Integer> includeTypeList;

    // exclude 类型 1-冲币 2-提币
    private List<Integer> excludeTypeList;

	// include 数量
	private List<BigDecimal> includeQuantityList;

    // exclude 数量
    private List<BigDecimal> excludeQuantityList;

	// include 转出地址
	private List<String> includeFromAddressList;

    // exclude 转出地址
    private List<String> excludeFromAddressList;

	// include 转入地址
	private List<String> includeToAddressList;

    // exclude 转入地址
    private List<String> excludeToAddressList;

	// include 合约地址
	private List<String> includeContractAddressList;

    // exclude 合约地址
    private List<String> excludeContractAddressList;

	// include 状态 1-处理中，2-成功 -1-失败 
	private List<Integer> includeStatusList;

    // exclude 状态 1-处理中，2-成功 -1-失败 
    private List<Integer> excludeStatusList;

	// include 发生日期 yyyyMMdd
	private List<Integer> includeOccurDateList;

    // exclude 发生日期 yyyyMMdd
    private List<Integer> excludeOccurDateList;

	// include 交易哈希值
	private List<String> includeTxHashList;

    // exclude 交易哈希值
    private List<String> excludeTxHashList;

	// include 重试次数
	private List<Integer> includeTimesList;

    // exclude 重试次数
    private List<Integer> excludeTimesList;

	// include 备注
	private List<String> includeRemarkList;

    // exclude 备注
    private List<String> excludeRemarkList;

	// include 成交时间戳
	private List<Long> includeTimestampList;

    // exclude 成交时间戳
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
