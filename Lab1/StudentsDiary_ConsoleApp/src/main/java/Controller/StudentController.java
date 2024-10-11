package Controller;

import Model.Student;
import View.StudentView;

/**
 * Controller responsible for managing the interaction between a student model and its view.
 * It allows modification of student data and updates the view accordingly.
 *
 * @author Adrian Reszka
 * @version 1.1.2
 */
public class StudentController {

    /**
     * The student object representing the current student being processed or viewed.
     */
    private Student student;

    /**
     * The view responsible for displaying detailed information about the student, such as their name, surname, and grades.
     */
    private StudentView studentView;

    /**
     * Constructs a new StudentController.
     *
     * @param model the student model to be managed
     * @param view the view to display the student details
     */
    public StudentController(Student model, StudentView view) {
        this.student = model;
        this.studentView = view;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id the new student ID
     */
    public void setStudentId(int id) {
        student.setId(id);
    }

    /**
     * Gets the ID of the student.
     *
     * @return the student ID
     */
    public int getStudentId() {
        return student.getId();
    }

    /**
     * Sets the name of the student.
     *
     * @param name the new student name
     */
    public void setStudentName(String name) {
        student.setName(name);
    }

    /**
     * Gets the name of the student.
     *
     * @return the student name
     */
    public String getStudentName() {
        return student.getName();
    }

    /**
     * Sets the surname of the student.
     *
     * @param surname the new student surname
     */
    public void setStudentSurname(String surname) {
        student.setSurname(surname);
    }

    /**
     * Gets the surname of the student.
     *
     * @return the student surname
     */
    public String getStudentSurname() {
        return student.getSurname();
    }

    /**
     * Adds a grade to the student.
     *
     * @param value the grade value
     * @param teacher the name of the teacher
     * @param subject the subject for the grade
     */
    public void addGrade(double value, String teacher, String subject) {
        student.addGrade(value, teacher, subject);
    }

    /**
     * Prints the grades of the student.
     */
    public void printGrades() {
        student.printGrades();
    }

    /**
     * Updates the view with the student details.
     */
    public void updateView() {
        studentView.printStudentDetails(student.getId(), student.getName(), student.getSurname());
    }
}
