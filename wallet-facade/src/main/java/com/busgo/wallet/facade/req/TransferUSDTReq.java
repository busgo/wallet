package com.busgo.wallet.facade.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 提币请求
 *
 * @author busgo
 * @date 2020-10-06 17:58
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferUSDTReq implements Serializable {

    // 提币唯一单据
    private String serialNo;

    // 用户id
    private Long userId;

    // 转入地址
    private String address;

    // 数量
    private BigDecimal quantity;


    public void checkParam() {

        Assert.hasText(this.serialNo, "serialNo not allow null");
        Assert.notNull(this.userId, "userId not allow null");
        Assert.hasText(this.address, "address not allow null");
        Assert.notNull(this.quantity, "quantity not allow null");
        Assert.isTrue(this.quantity.compareTo(BigDecimal.ZERO) > 0, "quantity not allow null");
    }
}
