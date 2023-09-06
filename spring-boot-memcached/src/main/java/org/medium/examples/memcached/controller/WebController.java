package org.medium.examples.memcached.controller;

import java.util.Calendar;
import java.util.Date;
import org.medium.examples.memcached.entity.Person;
import org.medium.examples.memcached.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("person")
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private PersonService personService;

    @GetMapping()
    public Person getPerson(@RequestParam String id) {

        Date startTime = Calendar.getInstance().getTime();
        Person person = personService.getPerson(id);
        Date endTime = Calendar.getInstance().getTime();
        LOGGER.info(
            "Time taken for the request: " + (endTime.getTime() - startTime.getTime()) + "ms");

        return person;
    }
    
    @PutMapping("/modify/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable String id) {
       return personService.updatePerson(person, id);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable String id) {
    	personService.deletePerson(id);
       return "Person with id: "+id+ " Deleted !";
    }

}
