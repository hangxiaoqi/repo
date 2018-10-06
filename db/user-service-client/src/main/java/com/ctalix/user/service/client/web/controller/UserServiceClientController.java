package com.ctalix.user.service.client.web.controller;

import com.ctalix.user.provider.api.UserServiceFacade;
import com.ctalix.user.provider.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link UserServiceFacade} 客户端 {@link RestController}
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */
@RestController
public class UserServiceClientController {

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    private  UserServiceFacade userServiceFacade;

    @Autowired
    public UserServiceClientController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/user/save/message")
    public boolean saveUserByMessage(@RequestBody User user) throws Exception {
        return userServiceFacade.saveUser(user);
    }
}
