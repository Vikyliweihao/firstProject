package com.hst.firstproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@MapperScan(basePackages = {"com.hst.firstproject.dao"})
@SpringBootApplication(scanBasePackages = {"com.hst.firstproject"})
@ServletComponentScan(basePackages = "com.hst.firstproject")
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
