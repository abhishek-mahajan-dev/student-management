package com.gta.studentapp.service;

import com.gta.studentapp.model.Student;
import com.gta.studentapp.model.Subject;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();
     private final Gson gson;
     private static final String DATA_FILE = "students.txt";

     private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

      public StudentService()
      {
         this.gson = new GsonBuilder().setPrettyPrinting().create();
         this.students = loadStudentsFromFile();
      }

       private List<Student> loadStudentsFromFile()
       {
        try (FileReader reader = new FileReader(DATA_FILE))
        {
             Type studentListType = new TypeToken<ArrayList<Student>>(){}.getType();
              List<Student> loadedStudents = gson.fromJson(reader, studentListType);
               return loadedStudents != null ? loadedStudents : new ArrayList<>();
        }
        catch (java.io.FileNotFoundException e)
        {
            return new ArrayList<>();
        }
        catch (IOException e)
        {
            return new ArrayList<>();
        }
        catch (com.google.gson.JsonSyntaxException e)
        {
            return new ArrayList<>();
        }
       }

       private void saveStudentsToFile()
       {
        try (FileWriter writer = new FileWriter(DATA_FILE))
        {
            gson.toJson(students, writer);
        }
        catch (IOException e)
        {
            logger.error("Error saving student data to {}: {}", DATA_FILE, e.getMessage(), e);
        }
       }

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
        saveStudentsToFile();
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