package com.ctalix.user.service.provider.fallback;

import com.ctalix.user.service.provider.api.UserServiceFacade;
import com.ctalix.user.service.provider.domain.User;

import java.util.ArrayList;

/**
 * {@link com.ctalix.user.service.provider.api.UserServiceFacade} Fallback 实现
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
    public ArrayList findAll() {
        return new ArrayList();
    }
}
