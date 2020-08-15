package com.tag.math.compute.controller;

import com.tag.math.compute.service.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/compute/")
public class ComputeController {

    @Autowired
    private ComputeService computeService;

    @GetMapping("/fib/{number}")
    public Long fib(@PathVariable int number){
        return this.computeService.findFib(number);
    }

    @GetMapping("/fact/{number}")
    public Long fact(@PathVariable int number){
        return this.computeService.findFact(number);
    }

    @GetMapping("/sum-fact/{number}")
    public Long sumFact(@PathVariable int number){
        return this.computeService.sumFact(number);
    }

}