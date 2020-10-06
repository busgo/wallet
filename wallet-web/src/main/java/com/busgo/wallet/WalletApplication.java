package com.busgo.wallet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author busgo
 * @date 2020-09-26 11:17
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.busgo.wallet.inner")
public class WalletApplication {


    public static void   main(String[] args){

        SpringApplication.run(WalletApplication.class,args);
    }
}
