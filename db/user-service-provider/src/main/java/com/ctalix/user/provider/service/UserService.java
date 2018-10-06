package com.ctalix.user.provider.service;

import com.ctalix.user.provider.domain.User;

import java.util.List;

/**
 * 用户 服务接口
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */

public interface UserService {

    /**
     * 添加一个用户
     *
     * @param user 用户实体对象
     * @return 是否添加成功
     * @throws Exception 保存用户可能会抛出的异常
     *
     */
    boolean saveUser(User user) throws Exception;

    /**
     * 获取所有的用户列表
     *
     * @return 用户集合
     *
     */
    List<User> findAll();
}
