package View;

import java.util.Scanner;

/**
 * The MainMenuView class represents the main menu interface for the student diary console application.
 * It provides methods to display the menu and capture user input for various operations such as adding,
 * removing, and editing student data and grades.
 *
 * @author Adrian Reszka
 * @version 1.0
 */
public class MainMenuView {


    /**
     * Constructor for MainMenuView class.
     * Initializes the scanners used for user input.
     */
    public MainMenuView() {}

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
}
