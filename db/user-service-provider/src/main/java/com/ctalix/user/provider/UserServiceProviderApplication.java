package com.ctalix.user.provider;

import com.ctalix.user.provider.stream.UserMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 用户服务提供类
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@EnableBinding(UserMessage.class)
public class UserServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceProviderApplication.class, args);
	}
}
