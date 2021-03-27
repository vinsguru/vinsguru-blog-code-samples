package com.vinsguru.webfluxprotobuf.service;

import com.vinsguru.models.Person;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PersonRepository {

    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private Map<Integer, Person> personMap;

    @PostConstruct
    private void init(){
        this.personMap = new HashMap<>();
        this.addPerson(Person.newBuilder().setName("vins").setAge(100).build());
    }

    public void addPerson(Person person){
        this.personMap.put(atomicInteger.getAndIncrement(), person);
    }

    public Person getPerson(Integer id){
        return this.personMap.getOrDefault(id, Person.getDefaultInstance());
    }

}
