package com.ctalix.user.provider.fallback;

import com.ctalix.user.provider.domain.User;
import com.ctalix.user.provider.api.UserServiceFacade;

import java.util.ArrayList;

/**
 * {@link UserServiceFacade} Fallback降级函数 实现
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */
public class UserServiceFallback implements UserServiceFacade {

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public ArrayList<User> findAll() {
        return new ArrayList<User>();
    }
}
