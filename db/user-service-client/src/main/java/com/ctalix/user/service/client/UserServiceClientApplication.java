package com.ctalix.user.service.client;

import com.ctalix.user.provider.api.UserServiceFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 引导类
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 0.0.1
 */
@SpringBootApplication
@RibbonClient("user-service-provider")
@EnableCircuitBreaker
@EnableFeignClients(clients = UserServiceFacade.class)
@EnableDiscoveryClient
public class UserServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceClientApplication.class, args);
    }


}
