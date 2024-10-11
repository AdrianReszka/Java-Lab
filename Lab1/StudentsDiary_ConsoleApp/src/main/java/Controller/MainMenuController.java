package Controller;

import Model.*;
import View.MainMenuView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller responsible for handling the main menu interactions in the application.
 * It coordinates actions between the view and the student list controller.
 *
 * @author Adrian Reszka
 * @version 1.1.4
 */
public class MainMenuController {


    /**
     * The controller responsible for managing student data, such as adding, removing, and updating students.
     */
    private StudentListController studentListController;

    /**
     * The view that displays the main menu and captures user input for various options.
     */
    private MainMenuView menuView;

    /**
     * The name of the file used for loading and saving the list of students and their data.
     */
    private String filename;

    /**
     * Constructs a new MainMenuController.
     *
     * @param studentListController the controller managing student data
     * @param menuView the view responsible for displaying the menu
     * @param filename the name of the file used for loading and saving data
     */
    public MainMenuController(StudentListController studentListController, MainMenuView menuView, String filename) {
        this.studentListController = studentListController;
        this.menuView = menuView;
        this.filename = filename;
    }

    /**
     * Starts the main loop of the application, displaying the menu and handling user choices.
     */
    public void start() {
        try {
            studentListController.getStudentList().loadFromFile(filename);
        } catch (FileLoadException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean running = true;

        while (running) {
            menuView.showMenu();
            int choice = menuView.getMenuChoice();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGradeToStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    removeGradeFromStudent();
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    editStudentData();
                    break;
                case 7:
                    editStudentGrade();
                    break;
                case 8:
                    exitProgram();
                    running = false;
                    break;
                default:
                    menuView.showInvalidOptionMessage();
            }
        }
    }

    /**
     * Adds a new student using inputs from the view.
     */
    private void addStudent() {
        int id = menuView.getStudentIdInput();
        String name = menuView.getStudentNameInput();
        String surname = menuView.getStudentSurnameInput();
        studentListController.createNewStudent(id, name, surname);
    }

    /**
     * Adds a grade to an existing student using inputs from the view.
     */
    private void addGradeToStudent() {
        int studentId = menuView.getStudentIdInput();
        String grade = menuView.getGradeInput();
        String teacher = menuView.getTeacherInput();
        String subject = menuView.getSubjectInput();
        studentListController.addGradeToStudent(studentId, grade, teacher, subject);
    }

    /**
     * Removes a student based on the provided ID.
     */
    private void removeStudent() {
        int studentId = menuView.getStudentIdInput();
        studentListController.removeStudent(studentId);
    }

    /**
     * Removes a grade from a student based on the provided index.
     */
    private void removeGradeFromStudent() {
        int studentId = menuView.getStudentIdInput();
        int gradeIndex = menuView.getGradeIndexInput() - 1;
        studentListController.removeGradeFromStudent(studentId, gradeIndex);
    }

    /**
     * Displays the list of students.
     */
    private void displayStudents() {
        studentListController.updateView();
    }

    /**
     * Edits the data of an existing student using inputs from the view.
     */
    private void editStudentData() {
        int studentId = menuView.getStudentIdInput();
        String newName = menuView.getStudentNameInput();
        String newSurname = menuView.getStudentSurnameInput();
        studentListController.editStudentData(studentId, newName, newSurname);
    }

    /**
     * Edits a grade for a student using inputs from the view.
     */
    private void editStudentGrade() {
        int studentId = menuView.getStudentIdInput();
        int gradeIndex = menuView.getGradeIndexInput() - 1;
        String newGrade = menuView.getGradeInput();
        String newTeacher = menuView.getTeacherInput();
        String newSubject = menuView.getSubjectInput();
        studentListController.editGradeForStudent(studentId, gradeIndex, newGrade, newTeacher, newSubject);
    }

    /**
     * Exits the program by saving the student data to a file and showing an exit message.
     */
    private void exitProgram() {
        try {
            studentListController.getStudentList().saveToFile(filename);
        } catch (FileSaveException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuView.showExitMessage();
    }
}
