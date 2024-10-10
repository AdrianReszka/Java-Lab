package View;

import Model.Student;

public class StudentView {
    public void printStudentDetails(int studentId, String studentName, String studentSurname) {
        System.out.println("Student: " + studentId);
        System.out.println("Name: " + studentName);
        System.out.println("Surname: " + studentSurname);
    }

    public void printStudentGrades(Student student) {
        student.printGrades();  
    }
}
