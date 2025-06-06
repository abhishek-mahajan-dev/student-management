package com.gta.studentapp.controller;

import com.gta.studentapp.model.Student;
import com.gta.studentapp.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional; 


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) 
    {
        this.studentService = studentService;
    }
     @PostMapping 
    public ResponseEntity<?> addStudent(@RequestBody Student student)
    {
        try {
            Student newStudent = studentService.addStudent(student);
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED); 
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
        } 
    }
     @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) Optional<String> name)
    {
        List<Student> students = studentService.getAllStudents(name);
        return new ResponseEntity<>(students, HttpStatus.OK); 
    }
    
}
