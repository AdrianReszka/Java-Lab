package Controller;

import Model.Student;
import View.StudentView;

public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentId(int id) {
        model.setId(id);
    }

    public int getStudentId() {
        return model.getId();
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentSurname(String surname) {
        model.setSurname(surname);
    }

    public String getStudentSurname() {
        return model.getSurname();
    }

    public void addGrade(double value, String teacher, String subject) {
        model.addGrade(value, teacher, subject);
    }

    public void printGrades() {
        model.printGrades();
    }

    public void updateView() {
        view.printStudentDetails(model.getId(), model.getName(), model.getSurname());
    }
}
