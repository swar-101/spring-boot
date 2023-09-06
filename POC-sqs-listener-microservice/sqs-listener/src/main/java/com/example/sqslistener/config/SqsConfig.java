package com.example.sqslistener.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.*;
import org.springframework.context.annotation.*;

@Configuration
public class SqsConfig {

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard().withCredentials(
                new DefaultAWSCredentialsProviderChain()).build();
    }
}
