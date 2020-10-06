package com.busgo.wallet.commons.exception;

import lombok.Getter;

/**
 * @author busgo
 * @date 2020-10-05 20:55
 **/
@Getter
public class WalletBizException extends RuntimeException {

    private int code;


    public WalletBizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public WalletBizException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public WalletBizException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
