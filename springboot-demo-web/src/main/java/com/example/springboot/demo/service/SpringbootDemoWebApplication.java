package com.example.springboot.demo.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.springboot.demo.dao")
public class SpringbootDemoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoWebApplication.class, args);
    }

}
