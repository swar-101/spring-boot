package com.example.reactivenewslettersubscription.reactivenewslettersubscription.repository;

import com.example.reactivenewslettersubscription.reactivenewslettersubscription.model.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findByEmail(String email);
    Mono<User> findByEmailAndConfirmed(String email, Boolean confirmed);
    Mono<Long> countByConfirmed(Boolean confirmed);
}
