package com.mycompany.studentsdiary_consoleapp;

import Controller.MainMenuController;
import Controller.StudentListController;
import Model.StudentList;
import View.MainMenuView;
import View.StudentListView;

/**
 * The StudentsDiary_ConsoleApp class is the main entry point for the student diary console application.
 * It sets up the main menu and controls the interaction between the user and the student list.
 *
 * @author Adrian Reszka
 * @version 1.3.2
 */
public class StudentsDiary_ConsoleApp {

    /**
     * The main method initializes the student list, views, and controllers,
     * and starts the main menu loop of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        StudentList studentList = new StudentList();
        StudentListView studentListView = new StudentListView();
        StudentListController studentListController = new StudentListController(studentList, studentListView);

        MainMenuView menuView = new MainMenuView();

        String filename = "studentList.txt";

        MainMenuController menuController = new MainMenuController(studentListController, menuView, filename);

        menuController.start();
    }
}
