/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

/**
 *
 * @author Kasper Siig
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PersonManager pm = new PersonManager();
        System.out.println("Teachers:");
        pm.printTeachers();
        System.out.println("\nStudents:");
        pm.printStudents();
    }
    
}
