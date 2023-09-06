package com.example.sqslistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.*;

@EnableSqs
@SpringBootApplication
public class SqsListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqsListenerApplication.class, args);
	}

}
