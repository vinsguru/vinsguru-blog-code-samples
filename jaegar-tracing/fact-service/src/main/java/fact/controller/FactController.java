package fact.controller;

import fact.service.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class FactController {

    @Autowired
    private FactService factService;

    @GetMapping("/fact/{number}")
    public Long calculate(@PathVariable("number") int number){
        System.out.println("Received fact request for :: " + number);
        long result = factService.calculateFactorial(number);
        System.out.println("Result :: " + result);
        return result;
    }

    @GetMapping("/health")
    public void health(){
        System.out.println("fact is up & healthy!");
    }
}
