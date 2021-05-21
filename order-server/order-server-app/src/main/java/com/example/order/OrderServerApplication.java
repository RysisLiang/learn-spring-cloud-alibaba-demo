package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = "com.example.common.framework.entity") // jpa扫描entity
public class OrderServerApplication {

    public static void main(String[] args) throws InterruptedException {
//        initFlowQpsRule(); //初始化限流规则

        ConfigurableApplicationContext applicationContext = SpringApplication.run(OrderServerApplication.class, args);
        String appName = applicationContext.getEnvironment().getProperty("spring.application.name");
        String port = applicationContext.getEnvironment().getProperty("server.port");
        System.err.printf("app = %s:%s%n", appName, port);
    }

}
