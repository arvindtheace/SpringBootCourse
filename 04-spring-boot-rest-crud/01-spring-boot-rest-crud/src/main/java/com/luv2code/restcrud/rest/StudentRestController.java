package com.luv2code.restcrud.rest;

import com.luv2code.restcrud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents = new ArrayList<>();

    // define @PostConstruct to load student data.. only once

    @PostConstruct
    public void loadData() {

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        // check if passed index is greater than array size or less than 0
        if(studentId > theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student not found with id " + studentId);
        }

        // index into the list and return the value

        return theStudents.get(studentId);
    }
}
