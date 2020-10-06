package com.busgo.wallet.facade.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 查询钱包账户
 *
 * @author busgo
 * @date 2020-10-06 17:49
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryWalletAccountReq implements Serializable {

    private Long userId;

    public void checkParam() {

        Assert.notNull(userId, "userId not allow null");
    }
}
