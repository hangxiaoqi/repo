package com.ctalix.user.provider.service.impl;

import com.ctalix.user.provider.domain.User;
import com.ctalix.user.provider.service.UserService;
import com.ctalix.user.provider.stream.UserMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ctalix.user.provider.stream.UserMessage.INPUT;
import static com.ctalix.user.provider.stream.UserMessage.OUTPUT;

/**
 * 用户服务 实现类
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */

@Service()
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMessage userMessage;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean saveUser(User user) throws Exception {
        // 将消息发送到kafka
        MessageChannel messageChannel = userMessage.input();
        String payload = objectMapper.writeValueAsString(user);
        GenericMessage<String> message = new GenericMessage<>(payload);
        return messageChannel.send(message);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    /**
     * 监听发送到kafka 的消息
     * @param message 消息主体
     */
    @StreamListener(INPUT)
    public void processIn(Message<?> message) {
        Object payload = message.getPayload();
        System.out.println("input streamListener -- " + payload);
    }

    /**
     * 监控 发送到kafka的消息
     * @param message 消息主体
     */
    @StreamListener(OUTPUT)
    public void processOut(Message<?> message) {
        Object payload = message.getPayload();
        System.out.println("out streamListener -- " + payload);
    }
}
