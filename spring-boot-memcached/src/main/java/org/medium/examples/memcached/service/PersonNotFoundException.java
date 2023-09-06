package org.medium.examples.memcached.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 7428051251365675318L;

   public PersonNotFoundException(String message) {
      super(message);
   }
}
