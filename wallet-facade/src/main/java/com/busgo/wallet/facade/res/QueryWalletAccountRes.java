package com.busgo.wallet.facade.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author busgo
 * @date 2020-10-06 17:49
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryWalletAccountRes implements Serializable {

    // 钱包地址
    private String address;
}
