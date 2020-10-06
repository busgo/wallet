package com.busgo.wallet.facade.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 创建钱包账户响应
 *
 * @author busgo
 * @date 2020-10-06 17:25
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWalletAccountRes implements Serializable {


    // 钱包地址
    private String address;

    // 用户id
    private Long userId;

}
