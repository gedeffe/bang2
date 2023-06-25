package com.supinfo.jee.casino;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class CasinoClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(CasinoClientApplication.class, args);
    }
}
