package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient // 服务注册于发现
//@EnableFeignClients(basePackages = {"com.example.account.rpc"}) // 开启feign
@EnableFeignClients // 开启feign
public class MainWebServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MainWebServerApplication.class, args);
        String appName = applicationContext.getEnvironment().getProperty("spring.application.name");
        String port = applicationContext.getEnvironment().getProperty("server.port");
        System.err.printf("app = %s:%s%n", appName, port);
    }

    /**
     * RestTemplate是消费者访问远程服务的工具，所以需要先注册到容器中。
     * LoadBalanced注解用于说明调用远程服务时使用负载均衡机制。
     *
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
