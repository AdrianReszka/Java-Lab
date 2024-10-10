package Model;

import java.util.ArrayList;

public class Student {
    private String name;
    private String surname;
    private int id;
    private ArrayList<Grade> grades;

    public Student(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.grades = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void addGrade(double value, String teacher, String subject) {
        Grade newGrade = new Grade(value, teacher, subject);
        grades.add(newGrade);
    }

    public boolean removeGrade(int index) {
        if (index >= 0 && index < grades.size()) {
            grades.remove(index);
            return true;
        }
        return false;
    }

    public void printGrades() {
        if (grades.isEmpty()) {
            System.out.println("Student " + name + " " + surname + " has no grades.");
        } else {
            System.out.println("Grades for " + name + " " + surname + ":");
            for (int i = 0; i < grades.size(); i++) {
                Grade grade = grades.get(i);
                System.out.println((i + 1) + ". " + String.format("%.2f", grade.getValue()) +
                        " (Teacher: " + grade.getTeacher() + ", Subject: " + grade.getSubject() + ")");
            }
        }
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }
}
