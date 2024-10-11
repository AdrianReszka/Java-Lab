package Model;

import java.io.*;
import java.util.ArrayList;

/**
 * The StudentList class manages a list of students and provides methods to add, remove, and find students.
 * It also allows saving and loading student data, including their grades, from a file.
 *
 * @author Adrian Reszka
 * @version 3.1.5
 */
public class StudentList {

    /**
     * A list of all students in the system. It is initialized as an empty list and is filled
     * with student objects when students are added.
     */
    public ArrayList<Student> students = new ArrayList<>();

    /**
     * Adds a new student to the list.
     *
     * @param id the student's ID
     * @param name the student's name
     * @param surname the student's surname
     */
    public void addStudent(int id, String name, String surname) {
        Student student = new Student(name, surname, id);
        students.add(student);
    }

    /**
     * Finds a student in the list by their ID.
     *
     * @param id the student's ID
     * @return the student with the specified ID, or null if not found
     */
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    /**
     * Removes a student from the list based on their ID.
     *
     * @param id the student's ID
     * @return true if the student was successfully removed, false if not found
     */
    public boolean removeStudentById(int id) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }

    /**
     * Saves the list of students and their grades to a specified file.
     *
     * @param filename the name of the file to save the data
     * @throws FileSaveException if an Input/Output error occurs while saving the file
     */
    public void saveToFile(String filename) throws FileSaveException {
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
        } catch (IOException e) {
            throw new FileSaveException(filename);
        }
    }

    /**
     * Loads the list of students and their grades from a specified file.
     *
     * @param filename the name of the file to load the data from
     * @throws FileLoadException if an Input/Output error occurs while loading the file
     */
    public void loadFromFile(String filename) throws FileLoadException {
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
                    double gradeValue = Double.parseDouble(data[0]);
                    String teacher = data[1];
                    String subject = data[2];
                    currentStudent.addGrade(gradeValue, teacher, subject);
                }
            }
        } catch (IOException e) {
            throw new FileLoadException(filename);
        }
    }
}
