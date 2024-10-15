package Controller;

import Model.*;
import Model.MessagePrinter;
import View.MainMenuView;

import java.io.IOException;
import java.util.Scanner;

/**
 * Controller responsible for handling the main menu interactions in the application.
 * It coordinates actions between the view and the student list controller.
 *
 * @author Adrian Reszka
 * @version 1.1.4
 */
public class MainMenuController {

    /**
     * A scanner object used to capture user input from the console for general menu selections and numeric inputs.
     */
    private Scanner scanner;

    /**
     * A scanner object configured to capture input from the console, specifically using a newline as the delimiter.
     * This is useful for reading strings with spaces.
     */
    private Scanner spacebarScanner;

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
     * The messagePrinter object responsible for printing the asking for input messages
     */
    private MessagePrinter messagePrinter;

    /**
     * Constructs a new MainMenuController.
     *
     * @param studentListController the controller managing student data
     * @param menuView the view responsible for displaying the menu
     * @param filename the name of the file used for loading and saving data
     */
    public MainMenuController(StudentListController studentListController, MainMenuView menuView, String filename) {
        this.scanner = new Scanner(System.in);
        this.spacebarScanner = new Scanner(System.in).useDelimiter("\n");
        this.studentListController = studentListController;
        this.menuView = menuView;
        this.filename = filename;
    }

    /**
     * Starts the main loop of the application, displaying the menu and handling user choices.
     */
    public void start() {
        studentListController.loadStudentListFromFile(filename);

        boolean running = true;

        while (running) {
            menuView.showMenu();
            int choice = getMenuChoice();

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
     * Captures the user's menu option choice.
     *
     * @return The user's selected option as an integer.
     */
    public int getMenuChoice() {
        return scanner.nextInt();
    }

    /**
     * Captures the student ID input from the user.
     *
     * @return The entered student ID as an integer.
     */
    public int getStudentIdInput() {
        messagePrinter = new MessagePrinter();
        int id = -1;
        while (true) {
            messagePrinter.printInputMessage("Enter student ID (must be an integer): ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                return id;
            } else {
                messagePrinter.printErrorMessage("Invalid input. Please enter a valid integer ID.");
                scanner.next();
                return -1;
            }
        }
    }

    /**
     * Captures the student name input from the user.
     *
     * @return The entered student name as a string.
     */
    public String getStudentNameInput() {
        messagePrinter = new MessagePrinter();
        messagePrinter.printInputMessage("Enter student name: ");
        return scanner.next();
    }

    /**
     * Captures the student surname input from the user.
     *
     * @return The entered student surname as a string.
     */
    public String getStudentSurnameInput() {
        messagePrinter = new MessagePrinter();
        messagePrinter.printInputMessage("Enter student surname: ");
        return scanner.next();
    }

    /**
     * Captures the grade input from the user.
     *
     * @return The entered grade value as a string.
     */
    public String getGradeInput() {
        messagePrinter = new MessagePrinter();
        messagePrinter.printInputMessage("Enter grade value (use dot or comma): ");
        return scanner.next();
    }

    /**
     * Captures the teacher's name input from the user.
     *
     * @return The entered teacher name as a string.
     */
    public String getTeacherInput() {
        messagePrinter = new MessagePrinter();
        messagePrinter.printInputMessage("Enter teacher name: ");
        return spacebarScanner.next();
    }

    /**
     * Captures the subject name input from the user.
     *
     * @return The entered subject name as a string.
     */
    public String getSubjectInput() {
        messagePrinter = new MessagePrinter();
        messagePrinter.printInputMessage("Enter subject name: ");
        return spacebarScanner.next();
    }

    /**
     * Captures the grade index input for removal or editing from the user.
     *
     * @return The entered grade index as an integer (1-based index).
     */
    public int getGradeIndexInput() {
        messagePrinter = new MessagePrinter();
        messagePrinter.printInputMessage("Enter grade index to remove/edit (1-based index): ");
        return scanner.nextInt();
    }

    /**
     * Displays an invalid option message when the user selects an invalid menu choice.
     */

    /**
     * Adds a new student using inputs from the view.
     */
    private void addStudent() {
        int id = getStudentIdInput();
        if (id == -1) {
            return;
        }

        String name = getStudentNameInput();
        String surname = getStudentSurnameInput();
        studentListController.createNewStudent(id, name, surname);
        messagePrinter = new MessagePrinter();
        messagePrinter.printSuccessMessage();
    }


    /**
     * Adds a grade to an existing student using inputs from the view.
     */
    private void addGradeToStudent() {
        int studentId = getStudentIdInput();
        if (studentId == -1) {
            return;
        }
        String grade = getGradeInput().replace(',', '.');
        String teacher = getTeacherInput();
        String subject = getSubjectInput();
        studentListController.addGradeToStudent(studentId, grade, teacher, subject);
    }


    /**
     * Removes a student based on the provided ID.
     */
    private void removeStudent() {
        int studentId = getStudentIdInput();
        studentListController.removeStudent(studentId);
    }

    /**
     * Removes a grade from a student based on the provided index.
     */
    private void removeGradeFromStudent() {
        int studentId = getStudentIdInput();
        int gradeIndex = getGradeIndexInput() - 1;
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
        int studentId = getStudentIdInput();
        String newName = getStudentNameInput();
        String newSurname = getStudentSurnameInput();
        studentListController.editStudentData(studentId, newName, newSurname);
    }

    /**
     * Edits a grade for a student using inputs from the view.
     */
    private void editStudentGrade() {
        int studentId = getStudentIdInput();
        int gradeIndex = getGradeIndexInput() - 1;
        String newGrade = getGradeInput().replace(',', '.');
        String newTeacher = getTeacherInput();
        String newSubject = getSubjectInput();
        studentListController.editGradeForStudent(studentId, gradeIndex, Double.parseDouble(newGrade), newTeacher, newSubject);
    }

    /**
     * Exits the program by saving the student data to a file and showing an exit message.
     */
    private void exitProgram() {
        studentListController.saveStudentListToFile(filename);
        menuView.showExitMessage();
    }
}
