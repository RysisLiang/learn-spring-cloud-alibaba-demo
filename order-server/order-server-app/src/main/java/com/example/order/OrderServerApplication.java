package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = "com.example.order.entity") // jpa扫描entity
public class OrderServerApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OrderServerApplication.class, args);
        String appName = applicationContext.getEnvironment().getProperty("spring.application.name");
        String port = applicationContext.getEnvironment().getProperty("server.port");
        System.err.printf("app = %s:%s%n", appName, port);

        // test
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        System.err.println("user name :" + userName + "; age: " + userAge);
        TimeUnit.SECONDS.sleep(1);
    }

}
