package View;

import java.util.Scanner;

/**
 * The MainMenuView class represents the main menu interface for the student diary console application.
 * It provides methods to display the menu and capture user input for various operations such as adding,
 * removing, and editing student data and grades.
 *
 * @author Adrian Reszka
 * @version 1.1.1
 */
public class MainMenuView {

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
     * Constructor for MainMenuView class.
     * Initializes the scanners used for user input.
     */
    public MainMenuView() {
        this.scanner = new Scanner(System.in);
        this.spacebarScanner = new Scanner(System.in).useDelimiter("\n");
    }

    /**
     * Displays the main menu options to the user.
     */
    public void showMenu() {
        System.out.println("\nStudents Diary!");
        System.out.println("Menu:");
        System.out.println("1. Add new student");
        System.out.println("2. Add grade to student");
        System.out.println("3. Remove student");
        System.out.println("4. Remove grade from student");
        System.out.println("5. Display all students and their grades");
        System.out.println("6. Edit student data");
        System.out.println("7. Edit student grade");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
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
        int id = -1;
        while (true) {
            System.out.print("Enter student ID (must be an integer): ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer ID.");
                scanner.next();
            }
        }
        return id;
    }

    /**
     * Captures the student name input from the user.
     *
     * @return The entered student name as a string.
     */
    public String getStudentNameInput() {
        System.out.print("Enter student name: ");
        return scanner.next();
    }

    /**
     * Captures the student surname input from the user.
     *
     * @return The entered student surname as a string.
     */
    public String getStudentSurnameInput() {
        System.out.print("Enter student surname: ");
        return scanner.next();
    }

    /**
     * Captures the grade input from the user.
     *
     * @return The entered grade value as a string.
     */
    public String getGradeInput() {
        System.out.print("Enter grade value (use dot or comma): ");
        return scanner.next();
    }

    /**
     * Captures the teacher's name input from the user.
     *
     * @return The entered teacher name as a string.
     */
    public String getTeacherInput() {
        System.out.print("Enter teacher name: ");
        return spacebarScanner.next();
    }

    /**
     * Captures the subject name input from the user.
     *
     * @return The entered subject name as a string.
     */
    public String getSubjectInput() {
        System.out.print("Enter subject name: ");
        return spacebarScanner.next();
    }

    /**
     * Captures the grade index input for removal or editing from the user.
     *
     * @return The entered grade index as an integer (1-based index).
     */
    public int getGradeIndexInput() {
        System.out.print("Enter grade index to remove/edit (1-based index): ");
        return scanner.nextInt();
    }

    /**
     * Displays an invalid option message when the user selects an invalid menu choice.
     */
    public void showInvalidOptionMessage() {
        System.out.println("Invalid option. Please try again.");
    }

    /**
     * Displays an exit message when the user chooses to exit the application.
     */
    public void showExitMessage() {
        System.out.println("Exiting program...");
    }
}
