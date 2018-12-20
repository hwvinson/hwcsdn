package com.hw.csdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CsdnApplication {
    public static void main(String[] args) {
        SpringApplication.run(CsdnApplication.class, args);
    }
}
