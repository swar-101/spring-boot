package com.example.microservices.camelmicroservice1.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSenderRouter extends RouteBuilder {

    @Override
    public void configure() {
        // timer
        from("timer:activemq-timer?period=10000")
        .transform().constant("My message for Active MQ")
        .to("activemq:my-active-mq-queue");
        //queue

    }

}
