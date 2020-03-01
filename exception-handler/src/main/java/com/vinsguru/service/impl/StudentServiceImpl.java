package com.vinsguru.service.impl;

import com.vinsguru.dto.Student;
import com.vinsguru.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class StudentServiceImpl implements StudentService {

    private List<Student> list;

    @PostConstruct
    private void init(){
        this.list = Arrays.asList(
                new Student(1, "fn1", "ln1"),
                new Student(2, "fn2", "ln2"),
                new Student(3, "fn3", "ln3"),
                new Student(4, "fn4", "ln4"),
                new Student(5, "fn5", "ln5")
        );
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
        int random = ThreadLocalRandom.current().nextInt(0, 10);

        if(random > 5)
            throw new Exception("random");

        return this.list;
    }

    @Override
    public Student findStudentById(int id) {
        return this.list
                    .stream()
                    .filter(s -> s.getId() == id)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
    }

}
