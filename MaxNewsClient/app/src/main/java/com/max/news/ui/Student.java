package com.max.news.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abc on 2016/12/9.
 */

public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Course> course = new ArrayList<>();

    public Student(String name, List<Course> course) {
        this.name = name;
        this.course = course;
    }

    public List<Course> getCourse(){
        return course;
    }
}
