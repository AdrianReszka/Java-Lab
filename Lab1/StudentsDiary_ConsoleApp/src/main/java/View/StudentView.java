package View;

import Model.Student;

/**
 * The StudentView class is responsible for printing detailed information about a single student,
 * including their personal data and grades.
 *
 * @author Adrian Reszka
 * @version 1.1.0
 */
public class StudentView {

    /**
     * Prints the details of a specific student.
     *
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param studentSurname The surname of the student.
     */
    public void printStudentDetails(int studentId, String studentName, String studentSurname) {
        System.out.println("Student: " + studentId);
        System.out.println("Name: " + studentName);
        System.out.println("Surname: " + studentSurname);
    }

    /**
     * Prints all grades for a specific student.
     *
     * @param student The student whose grades will be printed.
     */
    public void printStudentGrades(Student student) {
        student.printGrades();
    }
}
