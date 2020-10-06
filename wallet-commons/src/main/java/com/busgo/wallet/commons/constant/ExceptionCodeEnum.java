package com.busgo.wallet.commons.constant;

import lombok.Getter;

/**
 * @author busgo
 * @date 2020-10-05 21:01
 **/
@Getter
public enum  ExceptionCodeEnum {


    NewAccountError(-1001,"创建钱包地址失败");

    private int code;
    private String desc;


    ExceptionCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}


