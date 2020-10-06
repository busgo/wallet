package com.busgo.wallet.commons.constant;

/**
 * @author busgo
 * @date 2020-10-05 12:08
 **/
public interface UsdtTxRecordStatus {

    // 1-待处理 2-成功 -1-失败

    int No = 1;

    int Success = 2;

    int Fail = -1;

    // 不处理
    int None = -2;
}
