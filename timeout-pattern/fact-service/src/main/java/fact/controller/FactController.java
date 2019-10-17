package fact.controller;

import com.tag.dto.ResponseDto;
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
    public ResponseDto calculate(@PathVariable("number") int number){
        return factService.calculateFactorial(number);
    }

    @GetMapping("/health")
    public void health(){
        System.out.println("fact is up & healthy!");
    }
}
