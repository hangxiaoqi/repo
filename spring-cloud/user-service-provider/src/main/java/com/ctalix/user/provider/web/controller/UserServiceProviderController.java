package com.ctalix.user.provider.web.controller;

import com.ctalix.user.provider.api.UserServiceFacade;
import com.ctalix.user.provider.domain.User;
import com.ctalix.user.provider.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * 用户服务提供方 controller
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */
@RestController
public class UserServiceProviderController implements UserServiceFacade {

    @Autowired
    private UserService userService;

    @Override
    public boolean saveUser(User user) throws Exception {
        return userService.saveUser(user);
    }

    @HystrixCommand(
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            fallbackMethod = "fallbackFindAll")
    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * {@link #findAll()} 的失败降级函数
     * @return 空列表
     */
    public List<User> fallbackFindAll(){
        return Collections.emptyList();
    }

}
