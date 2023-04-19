package com.github.andregpereira.resilientshop.shoppingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaServer
@EnableFeignClients
@EnableZuulProxy
public class ShoppingapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingapiApplication.class, args);
    }

}
