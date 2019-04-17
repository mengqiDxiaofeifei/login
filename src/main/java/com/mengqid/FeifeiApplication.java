package com.mengqid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mengqid.mappers")
@EnableScheduling
public class FeifeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeifeiApplication.class, args);
    }

}
