package Controller;

import Model.Grade;
import Model.Student;
import Model.StudentList;
import View.StudentListView;

public class StudentListController {
    private StudentList studentList;
    private StudentListView studentListView;

    public StudentListController(StudentList studentList, StudentListView studentListView) {
        this.studentList = studentList;
        this.studentListView = studentListView;
    }

    public void createNewStudent(int id, String name, String surname) {
        studentList.addStudent(id, name, surname);
    }

    public void addGradeToStudent(int studentId, String gradeInput, String teacher, String subject) {
        gradeInput = gradeInput.replace(",", ".");
        try {
            double gradeValue = Double.parseDouble(gradeInput);
            Student student = studentList.findStudentById(studentId);
            if (student != null) {
                student.addGrade(gradeValue, teacher, subject);
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade format. Please use a number with a dot or comma.");
        }
    }

    public void editStudentData(int studentId, String newName, String newSurname) {
        Student student = studentList.findStudentById(studentId);
        if (student != null) {
            student.setName(newName);
            student.setSurname(newSurname);
            System.out.println("Student data updated.");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    public void editGradeForStudent(int studentId, int gradeIndex, String gradeInput, String newTeacher, String newSubject) {
        Student student = studentList.findStudentById(studentId);
        if (student != null) {
            gradeInput = gradeInput.replace(",", ".");
            try {
                double newGradeValue = Double.parseDouble(gradeInput);
                if (gradeIndex >= 0 && gradeIndex < student.getGrades().size()) {
                    Grade grade = student.getGrades().get(gradeIndex);
                    grade.setValue(newGradeValue);
                    grade.setTeacher(newTeacher);
                    grade.setSubject(newSubject);
                    System.out.println("Grade updated.");
                } else {
                    System.out.println("Invalid grade index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade format. Please use a number with a dot or comma.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    public void removeStudent(int studentId) {
        if (studentList.removeStudentById(studentId)) {
            System.out.println("Student with ID " + studentId + " has been removed.");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    public void removeGradeFromStudent(int studentId, int gradeIndex) {
        Student student = studentList.findStudentById(studentId);
        if (student != null) {
            if (student.removeGrade(gradeIndex)) {
                System.out.println("Grade " + (gradeIndex + 1) + " has been removed.");
            } else {
                System.out.println("Invalid grade index.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    public void updateView() {
        studentListView.printStudents(studentList.students);
    }
}
