package Controller;

import Model.*;
import View.StudentListView;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                try {
                    throw new StudentNotFoundException(studentId);
                } catch (StudentNotFoundException ex) {
                    Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (NumberFormatException e) {
            try {
                throw new InvalidGradeFormatException(gradeInput);
            } catch (InvalidGradeFormatException ex) {
                Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void editStudentData(int studentId, String newName, String newSurname) {
        Student student = studentList.findStudentById(studentId);
        if (student != null) {
            student.setName(newName);
            student.setSurname(newSurname);
            System.out.println("Student data updated.");
        } else {
            try {
                throw new StudentNotFoundException(studentId);
            } catch (StudentNotFoundException ex) {
                Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void editGradeForStudent(int studentId, int gradeIndex, String gradeInput, String newTeacher, String newSubject) {
        gradeInput = gradeInput.replace(",", ".");
        Student student = studentList.findStudentById(studentId);
        if (student != null) {
            try {
                double newGradeValue = Double.parseDouble(gradeInput);
                if (gradeIndex >= 0 && gradeIndex < student.getGrades().size()) {
                    Grade grade = student.getGrades().get(gradeIndex);
                    grade.setValue(newGradeValue);
                    grade.setTeacher(newTeacher);
                    grade.setSubject(newSubject);
                    System.out.println("Grade updated.");
                } else {
                    try {
                        throw new InvalidGradeIndexException(gradeIndex);
                    } catch (InvalidGradeIndexException ex) {
                        Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (NumberFormatException e) {
                try {
                    throw new InvalidGradeFormatException(gradeInput);
                } catch (InvalidGradeFormatException ex) {
                    Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new StudentNotFoundException(studentId);
            } catch (StudentNotFoundException ex) {
                Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void removeStudent(int studentId) {
        if (!studentList.removeStudentById(studentId)) {
            try {
                throw new StudentNotFoundException(studentId);
            } catch (StudentNotFoundException ex) {
                Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void removeGradeFromStudent(int studentId, int gradeIndex)  {
        Student student = studentList.findStudentById(studentId);
        if (student != null) {
            if (!student.removeGrade(gradeIndex)) {
                try {
                    throw new InvalidGradeIndexException(gradeIndex);
                } catch (InvalidGradeIndexException ex) {
                    Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new StudentNotFoundException(studentId);
            } catch (StudentNotFoundException ex) {
                Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateView() {
        studentListView.printStudents(studentList.students);
    }
    
    public StudentList getStudentList() {
        return studentList;
    }
}
