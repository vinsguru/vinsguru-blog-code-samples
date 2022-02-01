package com.vinsguru.exception;

public class StudentNotFoundException extends RuntimeException {

    private final int studentId;
    private static final String MESSAGE = "Student not found";

    public StudentNotFoundException(int id) {
        super(MESSAGE);
        this.studentId = id;
    }

    public int getStudentId() {
        return studentId;
    }

}
