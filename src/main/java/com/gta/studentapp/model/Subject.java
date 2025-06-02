package com.gta.studentapp.model;

public class Subject {

    private String name;
    private double marks;
    

public Subject() {}

public Subject(String name , double marks)
{
    this.name=name;
    this.marks=marks;
}


public String getName()
{
    return name;
}
public void setName(String name)
{
this.name=name;
}

public double getMarks()
{
    return marks;
}
public void setMarks(double marks)
{
    this.marks=marks;
}

}