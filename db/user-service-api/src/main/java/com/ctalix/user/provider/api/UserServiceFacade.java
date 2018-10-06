package com.ctalix.user.provider.api;

import com.ctalix.user.provider.domain.User;
import com.ctalix.user.provider.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 用户{@link User} 服务接口
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */
@FeignClient(name = "${user.service.name}", fallback = UserServiceFallback.class)
public interface UserServiceFacade {

    /**
     * 保存用户
     *
     * @param user 用户信息封装类
     * @return 是否添加成功
     * @throws Exception 保存用户的过程中可能会产生的异常
     */
    @PostMapping("/saveUser")
    boolean saveUser(User user) throws Exception;

    /**
     * 查找所有的用户
     *
     * @return 用户信息集合
     */
    @PostMapping("/findAll")
    List<User> findAll();

}