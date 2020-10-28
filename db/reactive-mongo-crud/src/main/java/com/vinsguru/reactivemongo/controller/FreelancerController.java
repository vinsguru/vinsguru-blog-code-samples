package com.vinsguru.reactivemongo.controller;

import com.vinsguru.reactivemongo.entity.Freelancer;
import com.vinsguru.reactivemongo.service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class FreelancerController {

    @Autowired
    private FreelancerService freelancerService;

    @GetMapping("/person/skills-one/{skills}")
    public Flux<Freelancer> findBySkills(@PathVariable List<String> skills){
        return this.freelancerService.findBySkillsOne(skills);
    }

    @GetMapping("/person/skills-all/{skills}")
    public Flux<Freelancer> findByAllSkills(@PathVariable List<String> skills){
        return this.freelancerService.findBySkillsAll(skills);
    }

    @GetMapping("/person/{id}")
    public Mono<Freelancer> getPerson(@PathVariable String id){
        return this.freelancerService.getPerson(id);
    }

    @PostMapping("/person")
    public Mono<Freelancer> createPerson(@RequestBody Freelancer person){
        return this.freelancerService.savePerson(person);
    }

    @PutMapping("/person")
    public Mono<Freelancer> updatePerson(@RequestBody Freelancer person){
        return this.freelancerService.updatePerson(person);
    }

    @DeleteMapping("/person/{id}")
    public Mono<Void> deletePerson(@PathVariable String id){
        return this.freelancerService.deletePerson(id);
    }

}
