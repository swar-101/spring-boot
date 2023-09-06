package com.example.reactivenewslettersubscription.reactivenewslettersubscription.model;

import org.springframework.data.mongodb.core.mapping.*;

import java.time.*;

@Document(collection = "subscriptions")
public class User {

    private String id;
    private String name;
    private String email;
    private LocalDateTime subscriptionDate;
    private Boolean confirmed;

    //Constructor
    public User(String id,String email, String name, Boolean confirmed,
                LocalDateTime subscriptionDate) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.subscriptionDate = subscriptionDate;
        this.confirmed = confirmed;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public void setConfirmedSubscription(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
