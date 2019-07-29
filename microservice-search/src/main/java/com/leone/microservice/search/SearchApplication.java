package com.leone.microservice.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * @author leone
 * @since 2019-06-27
 **/
@SpringBootApplication
@MapperScan("com.leone.microservice.search.mapper")
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
