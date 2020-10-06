package com.busgo.wallet.facade.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 转出 USDT 响应
 *
 * @author busgo
 * @date 2020-10-06 18:04
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferUSDTRes implements Serializable {

    // 交易唯一单据
    private String serialNo;

    // 交易哈希
    private String txHash;

    // 提币数量
    private BigDecimal quantity;

    // 转出状态
    private Integer status;


}
