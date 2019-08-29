package com.zhang.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhen  2019-07-16 17:03
 * <p>
 * DataApplication
 **/
@SpringBootApplication
@MapperScan("com.zhang.data.dao")
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}
