package Controller;

import Model.StudentList;
import View.MainMenuView;
import View.StudentListView;

public class MainMenuController {

    private StudentListController studentListController;
    private MainMenuView menuView;
    private String filename;

    public MainMenuController(StudentListController studentListController, MainMenuView menuView, String filename) {
        this.studentListController = studentListController;
        this.menuView = menuView;
        this.filename = filename;
    }

    public void start() {
        // Odczyt listy studentów z pliku na początku
        studentListController.getStudentList().loadFromFile(filename);

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

    private void addStudent() {
        int id = menuView.getStudentIdInput();
        String name = menuView.getStudentNameInput();
        String surname = menuView.getStudentSurnameInput();
        studentListController.createNewStudent(id, name, surname);
    }

    private void addGradeToStudent() {
        int studentId = menuView.getStudentIdInput();
        String grade = menuView.getGradeInput();
        String teacher = menuView.getTeacherInput();
        String subject = menuView.getSubjectInput();
        studentListController.addGradeToStudent(studentId, grade, teacher, subject);
    }

    private void removeStudent() {
        int studentId = menuView.getStudentIdInput();
        studentListController.removeStudent(studentId);
    }

    private void removeGradeFromStudent() {
        int studentId = menuView.getStudentIdInput();
        int gradeIndex = menuView.getGradeIndexInput() - 1;
        studentListController.removeGradeFromStudent(studentId, gradeIndex);
    }

    private void displayStudents() {
        studentListController.updateView();
    }

    private void editStudentData() {
        int studentId = menuView.getStudentIdInput();
        String newName = menuView.getStudentNameInput();
        String newSurname = menuView.getStudentSurnameInput();
        studentListController.editStudentData(studentId, newName, newSurname);
    }

    private void editStudentGrade() {
        int studentId = menuView.getStudentIdInput();
        int gradeIndex = menuView.getGradeIndexInput() - 1;
        String newGrade = menuView.getGradeInput();
        String newTeacher = menuView.getTeacherInput();
        String newSubject = menuView.getSubjectInput();
        studentListController.editGradeForStudent(studentId, gradeIndex, newGrade, newTeacher, newSubject);
    }

    private void exitProgram() {
        studentListController.getStudentList().saveToFile(filename);
        menuView.showExitMessage();
    }
}
