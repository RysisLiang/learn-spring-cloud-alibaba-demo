package com.example.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = "com.example.account.entity") // jpa扫描entity
public class UserServerApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserServerApplication.class, args);
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
