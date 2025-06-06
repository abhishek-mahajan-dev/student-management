package com.gta.studentapp.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String id;
    private String name;
    private List<Subject> subjects;
    private double averageMarks;
    private String grade;


    public Student()
    {
        this.subjects=new ArrayList<>();
    }

    public Student(String id,String name)
    {
        this.id=id;
        this.name=name;
        this.subjects=new ArrayList<>();
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id=id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    public List<Subject> getSubjects()
    {
        return subjects;
    }
      public void setSubjects(List<Subject> subjects)
    {
        this.subjects = subjects;
    }

    public double getAverageMarks()
    {
        return averageMarks;
    }
    public void setAverageMarks(double averageMarks)
    {
        this.averageMarks=averageMarks;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setGrade(String grade)
     {
        this.grade = grade;
    }  
}
