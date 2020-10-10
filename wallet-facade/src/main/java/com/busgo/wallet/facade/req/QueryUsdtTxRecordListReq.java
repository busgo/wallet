package com.busgo.wallet.facade.req;

import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author busgo
 * @date 2020-10-07 21:31
 **/
@Data
public class QueryUsdtTxRecordListReq implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    private Integer type;

    private Integer status;


    public void checkParam() {

        Assert.notNull(pageNo, "pageNo is not allow null");
        Assert.notNull(pageSize, "pageSize is not allow null");
    }
}
