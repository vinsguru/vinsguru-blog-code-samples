package com.vinsguru.controller;

import com.vinsguru.dto.Student;
import com.vinsguru.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAll() throws Exception {
        return this.studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") int id){
        return this.studentService.findStudentById(id);
    }

}
