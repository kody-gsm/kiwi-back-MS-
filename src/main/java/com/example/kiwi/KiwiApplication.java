package com.example.kiwi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example")
public class KiwiApplication {
    public static void main(String[] args) {
        SpringApplication.run(KiwiApplication.class, args);
    }

}
