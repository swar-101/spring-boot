package main.java.com.springhow.examples.springboot.rediscache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import main.java.com.springhow.examples.springboot.rediscache.entities.User;
import main.java.com.springhow.examples.springboot.rediscache.entities.UserRepository;

@RestController
public class UserController {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  private final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public User getUser(@PathVariable Long userId) {
    LOG.info("Getting user with ID {}.", userId);
    // return userRepository.findOne(Long.valueOf(userId));
    //return userRepository.findById(Long.valueOf(userId));
   User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
    //User user = userRepository.findOne(userId);
    
    
    return user;
  }
}
