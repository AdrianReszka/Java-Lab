package Controller;
import Model.*;
import View.StudentListView;
import Model.MessagePrinter;

import java.io.IOException;


/**
 * Controller responsible for managing a list of students and handling the interaction between the model and the view.
 * It includes functionalities like adding, removing, and editing students and their grades.
 *
 * @author YourName
 * @version 1.0.0
 */
public class StudentListController {

    /**
     * A list containing all students in the system. This list is used for managing student data,
     * such as adding, removing, or editing student information.
     */
    private StudentList studentList;

    /**
     * The view responsible for displaying the list of students and their data,
     * such as their names, IDs, and grades.
     */
    private StudentListView studentListView;

    /**
     * Constructs a new StudentListController.
     *
     * @param studentList the student list model to be managed
     * @param studentListView the view to display the list of students
     */
    public StudentListController(StudentList studentList, StudentListView studentListView) {
        this.studentList = studentList;
        this.studentListView = studentListView;
    }

    /**
     * Adds a new student to the list.
     *
     * @param id the student's ID
     * @param name the student's first name
     * @param surname the student's last name
     */
    public void createNewStudent(int id, String name, String surname) {
        MessagePrinter messagePrinter = new MessagePrinter();
        try {
            studentList.addStudent(id, name, surname);
        } catch (StudentAlreadyExistsException e) {
            messagePrinter.printErrorMessage(e.getMessage());
        } catch (StudentNotFoundException e) {
            messagePrinter.printSuccessMessage();
        }
    }

    /**
     * Removes a student from the list by ID.
     *
     * @param studentId the ID of the student to be removed
     */
    public void removeStudent(int studentId) {
        MessagePrinter messagePrinter = new MessagePrinter();
        try {
            studentList.removeStudent(studentId);
            messagePrinter.printSuccessMessage();
        } catch (StudentNotFoundException e) {
            messagePrinter.printErrorMessage(e.getMessage());
        }
    }


    /**
     * Edits the data of a specific student identified by ID.
     *
     * @param studentId the ID of the student to be edited
     * @param newName the new name for the student
     * @param newSurname the new surname for the student
     */
    public void editStudentData(int studentId, String newName, String newSurname) {
        MessagePrinter messagePrinter = new MessagePrinter();
        try {
            studentList.editStudentData(studentId, newName, newSurname);
            messagePrinter.printSuccessMessage();
        } catch (StudentNotFoundException e) {
            messagePrinter.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a grade to a specific student identified by ID.
     *
     * @param studentId the ID of the student
     * @param gradeInput the grade input string
     * @param teacher the name of the teacher assigning the grade
     * @param subject the subject for which the grade is given
     */
    public void addGradeToStudent(int studentId, String gradeInput, String teacher, String subject) {
        MessagePrinter messagePrinter = new MessagePrinter();
        try {
            studentList.addGradeToStudent(studentId, gradeInput, teacher, subject);
            messagePrinter.printSuccessMessage();
        } catch (StudentNotFoundException |  InvalidGradeFormatException | InvalidGradeIndexException e) {
            messagePrinter.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Removes a grade from a student identified by ID and grade index.
     *
     * @param studentId the ID of the student
     * @param gradeIndex the index of the grade to be removed
     */
    public void removeGradeFromStudent(int studentId, int gradeIndex) {
        MessagePrinter messagePrinter = new MessagePrinter();
        try {
            studentList.removeGradeFromStudent(studentId, gradeIndex);
            messagePrinter.printSuccessMessage();
        } catch (StudentNotFoundException | InvalidGradeIndexException e) {
            messagePrinter.printErrorMessage(e.getMessage());
        }
    }


    /**
     * Edits a specific grade of a student identified by ID and grade index.
     *
     * @param studentId the ID of the student
     * @param gradeIndex the index of the grade to be edited
     * @param newGradeValue the new grade value as a string
     * @param newTeacher the new teacher for the grade
     * @param newSubject the new subject for the grade
     */
    public void editGradeForStudent(int studentId, int gradeIndex, double newGradeValue, String newTeacher, String newSubject) {
        MessagePrinter messagePrinter = new MessagePrinter();
        try {
            studentList.editStudentGrade(studentId, gradeIndex, newGradeValue, newTeacher, newSubject);
            messagePrinter.printSuccessMessage();
        } catch (StudentNotFoundException | InvalidGradeIndexException | InvalidGradeFormatException e) {
            messagePrinter.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Saves the student list to a file.
     *
     * @param filename the name of the file to save to
     */
    public void saveStudentListToFile(String filename) {
        try {
            studentList.saveToFile(filename);
        } catch (IOException e) {
            MessagePrinter messagePrinter = new MessagePrinter();
            messagePrinter.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Loads the student list from a file.
     *
     * @param filename the name of the file to load from
     */
    public void loadStudentListFromFile(String filename) {
        try {
            studentList.loadFromFile(filename);
        } catch (IOException e) {
            MessagePrinter messagePrinter = new MessagePrinter();
            messagePrinter.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Updates the view with the current list of students.
     */
    public void updateView() {
        studentListView.printStudents(studentList.students);
    }

    /**
     * Returns the student list model.
     *
     * @return the StudentList object
     */
    public StudentList getStudentList() {
        return studentList;
    }
}





