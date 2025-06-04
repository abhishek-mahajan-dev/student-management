package com.gta.studentapp.service;

import com.gta.studentapp.model.Student;
import com.gta.studentapp.model.Subject;

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

        double average = calculateAverageMarks(student.getSubjects());
        student.setAverageMarks(average);
        student.setGrade(assignedGrade(average));


        students.add(student);
        return student;
    }

    private double calculateAverageMarks(List<Subject> subjects)
    {
        if (subjects == null || subjects.isEmpty())
        {
            return 0.0;
        }
        double totalMarks = subjects.stream()
                                   .mapToDouble(Subject::getMarks)
                                   .sum();

    return Math.round((totalMarks / subjects.size()) * 100.0) / 100.0;                           
    }

    private String assignedGrade(double averageMarks)
    {
        if (averageMarks >= 90) 
        {
            return "A";
        } else if (averageMarks >= 80) 
        {
            return "B";
        } else if (averageMarks >= 70)
         {
            return "C";
        } else if (averageMarks >= 60) 
        {
            return "D";
        } else
        {
            return "F";
        }
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