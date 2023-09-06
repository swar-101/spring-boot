package com.example.reactivenewslettersubscription.reactivenewslettersubscription.controller;

import com.example.reactivenewslettersubscription.reactivenewslettersubscription.model.*;
import com.example.reactivenewslettersubscription.reactivenewslettersubscription.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import java.time.*;

@RestController
@RequestMapping("/newsletter")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping(path = "/subscribe")
    public Mono<User> subscribe(@RequestBody User user) {

        user.setSubscriptionDate(LocalDateTime.now());

        return userRepository.save(user);
    }

    @PostMapping(path = "/confirm")
    public Flux<User> confirm(@RequestParam String email) {
        return userRepository.findByEmail(email)
                .flatMap(user -> {
                    user.setConfirmedSubscription(true);
                    return userRepository.save(user);
        });
    }

    @GetMapping(path = "/users")
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
}
