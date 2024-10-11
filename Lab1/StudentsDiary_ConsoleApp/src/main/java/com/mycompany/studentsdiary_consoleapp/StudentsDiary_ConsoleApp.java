package com.mycompany.studentsdiary_consoleapp;

import Controller.MainMenuController;
import Controller.StudentListController;
import Model.StudentList;
import View.MainMenuView;
import View.StudentListView;

public class StudentsDiary_ConsoleApp {

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
