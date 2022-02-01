package com.vinsguru.service;

import com.vinsguru.dto.Student;
import com.vinsguru.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class StudentService {

    private static final Map<Integer, Student> DB = Map.of(
            1, new Student(1, "fn1", "ln1"),
            2, new Student(2, "fn2", "ln2"),
            3, new Student(3, "fn3", "ln3"),
            4, new Student(4, "fn4", "ln4"),
            5, new Student(5, "fn5", "ln5")
    );

    public Flux<Student> getAllStudents() throws Exception {
        return Flux.fromIterable(DB.values())
                   .doFirst(this::throwRandomError);
    }

    public Mono<Student> findStudentById(int id) {
        return Mono.just(id)
                   .filter(DB::containsKey)
                   .map(DB::get)
                   .switchIfEmpty(Mono.error(() -> new StudentNotFoundException(id)));
    }

    private void throwRandomError(){
        var random = ThreadLocalRandom.current().nextInt(0, 10);
        if(random > 5)
            throw new RuntimeException("some random error");
    }

}
