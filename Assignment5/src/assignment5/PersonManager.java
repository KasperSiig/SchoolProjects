/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import assignment5.BE.Person;
import assignment5.BE.Student;
import assignment5.BE.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kasper Siig
 */
public class PersonManager {
    
    private List<Person> persons;

    public PersonManager() {
        persons = new ArrayList();
        
        addTeachers();
        
        addStudents();
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }

    private void addStudents() {
        Student a = new Student(105, "Bo Ibesen", "CS");
        a.setEmail("bib@easv.dk");
        a.addGrade(new GradeInfo("ITO", 5));
        a.addGrade(new GradeInfo("SCO", 7));
        a.addGrade(new GradeInfo("SDE", 8));

        persons.add(a);
        
        Student b = new Student(106, "Ib Boesen", "CS");
        b.setEmail("bib@easv.dk");
        b.addGrade(new GradeInfo("ITO", 7));
        b.addGrade(new GradeInfo("SCO", 6));
        b.addGrade(new GradeInfo("SDE", 2));

        persons.add(b);
    }

    private void addTeachers() {
        Teacher a = new Teacher(202, "Bent Pedersen", "bp");
        a.addSubject("Programming");
        a.addSubject("Game Dev");
        a.setEmail("bp@easv.dk");
        persons.add(a);
        
        Teacher b = new Teacher(203, "Peter B. Hansen", "pbh");
        b.addSubject("SDE");
        b.addSubject("SCO");
        b.setEmail("phb@easv.dk");
        persons.add(b);
    }
    
    public void printTeachers() {
        System.out.println("ID\tName\t\tEmail\t\tInitials\tMain\t");
        for (Person person : persons) {
            if (person instanceof Teacher) {
                System.out.println(person.toString());
            } 
        }
    }
    
    public void printStudents() {
        System.out.println("ID\tName\t\tEmail\t\tEducation\tAvg. Grade");
        for (Person person : persons) {
            if (person instanceof Student) {
                System.out.println(person.toString());
            } 
        }
    }
    
}
