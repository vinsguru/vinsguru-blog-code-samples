package com.vinsguru.webfluxprotobuf.controller;

import com.vinsguru.models.Person;
import com.vinsguru.webfluxprotobuf.service.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("{id}")
    public Person getPerson(@PathVariable int id){
        return this.personRepository.getPerson(id);
    }

    @PostMapping
    public void createPerson(@RequestBody Person person){
        this.personRepository.addPerson(person);
    }

}
