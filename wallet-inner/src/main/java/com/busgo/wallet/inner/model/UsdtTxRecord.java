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
public class UsdtTxRecord implements Serializable {



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


}
