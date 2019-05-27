package com.leone.microservice.transaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-14
 **/
@SpringBootApplication
@MapperScan("com.leone.microservice.transaction.mapper")
public class TransactionApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TransactionApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
