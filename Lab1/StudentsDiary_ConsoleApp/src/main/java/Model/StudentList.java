package Model;

import java.io.*;
import java.util.ArrayList;

public class StudentList {
    public ArrayList<Student> students = new ArrayList<>();

    public void addStudent(int id, String name, String surname) {
        Student student = new Student(name, surname, id);
        students.add(student);
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean removeStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.getId() + ";" + student.getName() + ";" + student.getSurname());
                writer.newLine();

                for (Grade grade : student.getGrades()) {
                    writer.write(grade.getValue() + ";" + grade.getTeacher() + ";" + grade.getSubject());
                    writer.newLine();
                }
                writer.write("END"); 
                writer.newLine();
            }
            System.out.println("Student list saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving student list: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Student currentStudent = null;

            while ((line = reader.readLine()) != null) {
                if (line.equals("END")) {
                    currentStudent = null;  
                    continue;
                }

                String[] data = line.split(";");

                if (data.length == 3 && currentStudent == null) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String surname = data[2];
                    currentStudent = new Student(name, surname, id);
                    students.add(currentStudent);
                } else if (data.length == 3 && currentStudent != null) {
                    try {
                        double gradeValue = Double.parseDouble(data[0]);
                        String teacher = data[1];
                        String subject = data[2];
                        currentStudent.addGrade(gradeValue, teacher, subject);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade format in file for student: " + currentStudent.getName());
                    }
                }
            }
            System.out.println("Student list loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename + ". Starting with an empty list.");
        } catch (IOException e) {
            System.out.println("Error loading student list: " + e.getMessage());
        }
    }

}
