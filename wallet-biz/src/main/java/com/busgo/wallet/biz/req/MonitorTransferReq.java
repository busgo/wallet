package com.busgo.wallet.biz.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 监听合约交易请求
 *
 * @author busgo
 * @date 2020-09-26 11:31
 **/
@Data
public class MonitorTransferReq implements Serializable {

    // 合约地址
    private String contractAddress;

    // 区块高度
    private String firstBlock;
}
