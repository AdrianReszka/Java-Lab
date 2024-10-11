package View;

import java.util.Scanner;

public class MainMenuView {

    private Scanner scanner;
    private Scanner spacebarScanner;

    public MainMenuView() {
        this.scanner = new Scanner(System.in);
        this.spacebarScanner = new Scanner(System.in).useDelimiter("\n");
    }

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

    public int getMenuChoice() {
        return scanner.nextInt();
    }

    public int getStudentIdInput() {
        System.out.print("Enter student ID: ");
        return scanner.nextInt();
    }

    public String getStudentNameInput() {
        System.out.print("Enter student name: ");
        return scanner.next();
    }

    public String getStudentSurnameInput() {
        System.out.print("Enter student surname: ");
        return scanner.next();
    }

    public String getGradeInput() {
        System.out.print("Enter grade value (use dot or comma): ");
        return scanner.next();
    }

    public String getTeacherInput() {
        System.out.print("Enter teacher name: ");
        return spacebarScanner.next();
    }

    public String getSubjectInput() {
        System.out.print("Enter subject name: ");
        return spacebarScanner.next();
    }

    public int getGradeIndexInput() {
        System.out.print("Enter grade index to remove/edit (1-based index): ");
        return scanner.nextInt();
    }

    public void showInvalidOptionMessage() {
        System.out.println("Invalid option. Please try again.");
    }

    public void showExitMessage() {
        System.out.println("Exiting program...");
    }
}
