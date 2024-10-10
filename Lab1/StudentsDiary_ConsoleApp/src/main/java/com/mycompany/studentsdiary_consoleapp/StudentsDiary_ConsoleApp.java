package com.mycompany.studentsdiary_consoleapp;

import Controller.StudentListController;
import Model.StudentList;
import View.StudentListView;

import java.util.Scanner;

public class StudentsDiary_ConsoleApp {

    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        StudentListView studentListView = new StudentListView();
        StudentListController studentListController = new StudentListController(studentList, studentListView);

        String filename = "studentList.txt";

        studentList.loadFromFile(filename);

        Scanner scanner = new Scanner(System.in);
        Scanner spacebarScanner = new Scanner(System.in).useDelimiter("\n");
        
        boolean running = true;

        while (running) {
            System.out.println("\nStudents Dairy!");
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

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    System.out.print("Enter student surname: ");
                    String surname = scanner.next();
                    studentListController.createNewStudent(id, name, surname);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    int studentIdForGrade = scanner.nextInt();
                    System.out.print("Enter grade value: ");
                    String gradeInput = scanner.next();
                    System.out.print("Enter teacher name: ");
                    String teacher = spacebarScanner.next();
                    System.out.print("Enter subject name: "); 
                    String subject = spacebarScanner.next();
                    studentListController.addGradeToStudent(studentIdForGrade, gradeInput, teacher, subject);
                    break;

                case 3:
                    System.out.print("Enter student ID to remove: ");
                    int studentIdToRemove = scanner.nextInt();
                    studentListController.removeStudent(studentIdToRemove);
                    break;

                case 4:
                    System.out.print("Enter student ID to remove grade from: ");
                    int studentIdForGradeRemoval = scanner.nextInt();
                    System.out.print("Enter grade index to remove: ");
                    int gradeIndex = scanner.nextInt() - 1;
                    studentListController.removeGradeFromStudent(studentIdForGradeRemoval, gradeIndex);
                    break;

                case 5:
                    studentListController.updateView();
                    break;

                case 6:
                    System.out.print("Enter student ID to edit: ");
                    int studentIdToEdit = scanner.nextInt();
                    System.out.print("Enter new student name: ");
                    String newName = scanner.next();
                    System.out.print("Enter new student surname: ");
                    String newSurname = scanner.next();
                    studentListController.editStudentData(studentIdToEdit, newName, newSurname);
                    break;

                case 7:
                    System.out.print("Enter student ID: ");
                    int studentIdForGradeEdit = scanner.nextInt();
                    System.out.print("Enter grade index to edit: ");
                    int gradeIndexToEdit = scanner.nextInt() - 1;
                    System.out.print("Enter new grade value: ");
                    String newGradeInput = scanner.next();
                    System.out.print("Enter new teacher name: ");
                    String newTeacher = spacebarScanner.next();
                    System.out.print("Enter new subject name: "); 
                    String newSubject = spacebarScanner.next();
                    studentListController.editGradeForStudent(studentIdForGradeEdit, gradeIndexToEdit, newGradeInput, newTeacher, newSubject);
                    break;

                case 8:
                    studentList.saveToFile(filename);
                    running = false;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
