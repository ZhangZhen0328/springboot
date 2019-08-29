package com.zhang.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhen  2019-07-16 17:33
 * <p>
 * WebapiApplication
 **/
@SpringBootApplication(scanBasePackages = {"com.zhang.data","com.zhang.webapi","com.zhang.core"})
@MapperScan("com.zhang.data.dao")
public class WebapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebapiApplication.class, args);
    }
}
