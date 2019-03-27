package com.mengqid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mengqid.mappers")
public class FeifeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeifeiApplication.class, args);
    }

}
