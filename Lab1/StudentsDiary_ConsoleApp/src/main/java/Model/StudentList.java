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
     * @throws StudentAlreadyExistsException if the student with specified id exists
     * @throws StudentNotFoundException if the student does not already exist
     */
    public void addStudent(int id, String name, String surname) throws StudentAlreadyExistsException, StudentNotFoundException {
        Student student = new Student(name, surname, id);
        Student existingStudent = findStudentById(id);

        if (existingStudent != null) {
            throw new StudentAlreadyExistsException();
        }
        students.add(student);
    }

    /**
     * Removes a student from the list based on their ID.
     *
     * @param id the student's ID
     * @throws StudentNotFoundException if student with specified id was not found
     */
    public void removeStudent(int id) throws StudentNotFoundException {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            return;
        }
        throw new StudentNotFoundException();
    }

    /**
     * Edits the data of a specific student identified by ID.
     *
     * @param studentId the ID of the student to be edited
     * @param newName the new name for the student
     * @param newSurname the new surname for the student
     * @throws StudentNotFoundException if the student with specified id was not found
     */
    public void editStudentData(int studentId, String newName, String newSurname) throws StudentNotFoundException {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        student.setName(newName);
        student.setSurname(newSurname);
    }

    /**
     * Adds a grade to a specific student identified by ID.
     *
     * @param studentId the ID of the student
     * @param gradeInput the grade input string
     * @param teacher the name of the teacher assigning the grade
     * @param subject the subject for which the grade is given
     * @throws StudentNotFoundException if the student with specified id was not found
     * @throws InvalidGradeIndexException if the grade with specified index was not found
     * @throws InvalidGradeFormatException if the grade format is incorrect
     */
    public void addGradeToStudent(int studentId, String gradeInput, String teacher, String subject) throws InvalidGradeIndexException, StudentNotFoundException, InvalidGradeFormatException {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        try {
            double gradeValue = Double.parseDouble(gradeInput);
            if (gradeValue < 1.0 || gradeValue > 5.0) {
                throw new InvalidGradeIndexException();
            }
            student.addGrade(gradeValue, teacher, subject);
        } catch (NumberFormatException e) {
            throw new InvalidGradeFormatException();
        }
    }

    /**
     * Removes a grade from a student identified by ID and grade index.
     *
     * @param studentId the ID of the student
     * @param gradeIndex the index of the grade to be removed
     * @throws StudentNotFoundException if the student with specified id was not found
     * @throws InvalidGradeIndexException if the grade with specified index was not found
     */
    public void removeGradeFromStudent(int studentId, int gradeIndex) throws StudentNotFoundException, InvalidGradeIndexException {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        boolean isRemoved = student.removeGrade(gradeIndex);
        if (!isRemoved) {
            throw new InvalidGradeIndexException();
        }
    }

    /**
     * Edits a specific grade of a student identified by ID and grade index.
     *
     * @param studentId the ID of the student
     * @param gradeIndex the index of the grade to be edited
     * @param newGradeValue the new grade value as a string
     * @param newTeacher the new teacher for the grade
     * @param newSubject the new subject for the grade
     * @throws StudentNotFoundException if the student with specified id was not found
     * @throws InvalidGradeIndexException if the grade with specified index was not found
     * @throws InvalidGradeFormatException if the grade format is incorrect
     */
    public void editStudentGrade(int studentId, int gradeIndex, double newGradeValue, String newTeacher, String newSubject)
            throws StudentNotFoundException, InvalidGradeIndexException, InvalidGradeFormatException {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        if (gradeIndex < 0 || gradeIndex >= student.getGrades().size()) {
            throw new InvalidGradeIndexException();
        }
        if (newGradeValue < 1.0 || newGradeValue > 5.0) {
            throw new InvalidGradeFormatException();
        }
        Grade grade = student.getGrades().get(gradeIndex);
        grade.setValue(newGradeValue);
        grade.setTeacher(newTeacher);
        grade.setSubject(newSubject);
    }

    /**
     * Saves the list of students and their grades to a specified file.
     *
     * @param filename the name of the file to save the data
     * @throws IOException if an Input/Output error occurs while saving the file
     */
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename)); {
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
        } writer.close();
    }

    /**
     * Finds a student by their ID.
     *
     * @param id the ID of the student to find
     * @return the Student object if found
     * @throws StudentNotFoundException if no student with the given ID exists
     */
    public Student findStudentById(int id) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new StudentNotFoundException();
    }

    /**
     * Loads the list of students and their grades from a specified file.
     *
     * @param filename the name of the file to load the data from
     * @throws IOException if an Input/Output error occurs while loading the file
     */
    public void loadFromFile(String filename) throws IOException {
         BufferedReader reader = new BufferedReader(new FileReader(filename)); {
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
        } reader.close();
    }
}
