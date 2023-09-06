package com.example.sqslistener.listener;

import lombok.extern.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cloud.aws.messaging.listener.*;
import org.springframework.cloud.aws.messaging.listener.annotation.*;
import org.springframework.stereotype.*;

@Log4j2
@Component
public class UserRegisteredListener {

    @Autowired
    public UserRegisteredListener() {
    }

    @SqsListener(value = "${aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveUserInfo(String message) {
        if (!message.isEmpty()) {
            log.info("Received message: {}", message);
        }
    }
}
