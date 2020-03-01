package com.vinsguru.service;

import com.vinsguru.dto.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents() throws Exception;
    Student findStudentById(int id);

}
