package com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook;

import com.udemy.chaddarby.spring_boot_unit_testing.model.gradebook.CollegeStudentGradeBook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Gradebook {

    private List<CollegeStudentGradeBook> students = new ArrayList<>();

    public Gradebook() {

    }
    public Gradebook(List<CollegeStudentGradeBook> students) {
        this.students = students;
    }

    public List<CollegeStudentGradeBook> getStudents() {
        return students;
    }

    public void setStudents(List<CollegeStudentGradeBook> students) {
        this.students = students;
    }
}
