package com.ctalix.user.service.provider.api;

import com.ctalix.user.service.provider.domain.User;
import com.ctalix.user.service.provider.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

/**
 * 用户服务
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */
@FeignClient(name = "${user.service.name}",fallback = UserServiceFallback.class)
public interface UserServiceFacade {

    /**
     * 保存用户
     * @param user 用户信息封装类
     * @return 是否添加成功
     */
    @PostMapping
    boolean saveUser(User user);

    /**
     * 查找所有的用户
     * @return 用户信息集合
     */
    @PostMapping
    ArrayList findAll();


}
