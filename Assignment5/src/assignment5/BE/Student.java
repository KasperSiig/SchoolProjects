/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5.BE;

import assignment5.GradeInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kasper Siig
 */
public class Student extends Person {

    private List<GradeInfo> gradeReport = new ArrayList();
    private String education;

    public Student(int id, String name, String education) {
        super(id, name);
        this.education = education;
    }

    public List<GradeInfo> getGradeReport() {
        return gradeReport;
    }

    public String getEducation() {
        return education;
    }

    public double getAverageGrade() {
        double total = 0;
        double size = gradeReport.size();
        double result = 0;

        for (GradeInfo gradeInfo : gradeReport) {
            total += gradeInfo.getGrade();
        }
        if (total != 0) {
            result = total / size;
        }

        return result;
    }

    public int getGrade(String subject) {
        for (GradeInfo gradeInfo : gradeReport) {
            if (gradeInfo.getSubject().equals(subject)) {
                return gradeInfo.getGrade();
            }
        }
        return -1;
    }

    public void addGrade(GradeInfo grade) {
        gradeReport.add(grade);
    }

    @Override
    public String toString() {
        return super.toString() + "\t" + education + "\t\t" + getAverageGrade();
    }

}
