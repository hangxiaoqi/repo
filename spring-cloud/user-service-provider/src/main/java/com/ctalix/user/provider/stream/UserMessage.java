package com.ctalix.user.provider.stream;

import com.ctalix.user.provider.domain.User;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * {@link User} kafka stream 定义
 *
 * @author <a href="mailto:gcx909109@sina.cn">弓成龙</a>
 * @since 1.0.0
 */
public interface UserMessage {

    String INPUT = "usermq";
    String OUTPUT = "usermq-out";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();
}

