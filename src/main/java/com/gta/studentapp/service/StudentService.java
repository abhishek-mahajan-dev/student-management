package com.gta.studentapp.service;

import com.gta.studentapp.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public Student addStudent(Student student)
    {
        
        if (getStudentById(student.getId()).isPresent()) 
        {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " already exists.");
        }
        students.add(student);
        return student;
    }


public List<Student> getAllStudents() 
    {
        return new ArrayList<>(students); 
    }

public Optional<Student> getStudentById(String id) 
    {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }    

}