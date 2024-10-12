package Controller;
import Model.*;
import View.StudentListView;


/**
 * Controller responsible for managing a list of students and handling the interaction between the model and the view.
 * It includes functionalities like adding, removing, and editing students and their grades.
 *
 * @author YourName
 * @version 1.0.0
 */
public class StudentListController {

    /**
     * A list containing all students in the system. This list is used for managing student data,
     * such as adding, removing, or editing student information.
     */
    private StudentList studentList;

    /**
     * The view responsible for displaying the list of students and their data,
     * such as their names, IDs, and grades.
     */
    private StudentListView studentListView;

    /**
     * Constructs a new StudentListController.
     *
     * @param studentList the student list model to be managed
     * @param studentListView the view to display the list of students
     */
    public StudentListController(StudentList studentList, StudentListView studentListView) {
        this.studentList = studentList;
        this.studentListView = studentListView;
    }

    /**
     * Adds a new student to the list.
     *
     * @param id the student's ID
     * @param name the student's first name
     * @param surname the student's last name
     */
    public void createNewStudent(int id, String name, String surname) {
        try {
            Student existingStudent = studentList.findStudentById(id);
            if (existingStudent != null) {
                throw new StudentAlreadyExistsException(id);
            }
            studentList.addStudent(id, name, surname);
            System.err.println("Student with ID " + id + " has been added.");
        } catch (StudentAlreadyExistsException | StudentNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Adds a grade to a specific student identified by ID.
     *
     * @param studentId the ID of the student
     * @param gradeInput the grade input string
     * @param teacher the name of the teacher assigning the grade
     * @param subject the subject for which the grade is given
     */
    public void addGradeToStudent(int studentId, String gradeInput, String teacher, String subject) {
        gradeInput = gradeInput.replace(",", ".");
        try {
            double gradeValue = Double.parseDouble(gradeInput);
            Student student = studentList.findStudentById(studentId);
            if (student != null) {
                student.addGrade(gradeValue, teacher, subject);
            } else {
                throw new StudentNotFoundException(studentId);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid grade format: " + gradeInput);
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage() + " for student ID: " + studentId);
        }
    }



    /**
     * Edits the data of a specific student identified by ID.
     *
     * @param studentId the ID of the student to be edited
     * @param newName the new name for the student
     * @param newSurname the new surname for the student
     */
    public void editStudentData(int studentId, String newName, String newSurname) {
        try {
            Student student = studentList.findStudentById(studentId);
            if (student != null) {
                student.setName(newName);
                student.setSurname(newSurname);
                System.out.println("Student data updated.");
            } else {
                throw new StudentNotFoundException(studentId);
            }
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage() + " for student ID: " + studentId);
        }
    }


    /**
     * Edits a specific grade of a student identified by ID and grade index.
     *
     * @param studentId the ID of the student
     * @param gradeIndex the index of the grade to be edited
     * @param gradeInput the new grade value as a string
     * @param newTeacher the new teacher for the grade
     * @param newSubject the new subject for the grade
     */
    public void editGradeForStudent(int studentId, int gradeIndex, String gradeInput, String newTeacher, String newSubject) {
        gradeInput = gradeInput.replace(",", ".");
        try {
            double newGradeValue = Double.parseDouble(gradeInput);
            Student student = studentList.findStudentById(studentId);
            if (student != null) {
                if (gradeIndex >= 0 && gradeIndex < student.getGrades().size()) {
                    Grade grade = student.getGrades().get(gradeIndex);
                    grade.setValue(newGradeValue);
                    grade.setTeacher(newTeacher);
                    grade.setSubject(newSubject);
                    System.out.println("Grade updated.");
                } else {
                    throw new InvalidGradeIndexException(gradeIndex);
                }
            } else {
                throw new StudentNotFoundException(studentId);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid grade format: " + gradeInput);
        } catch (InvalidGradeIndexException e) {
            System.err.println(e.getMessage() + " for grade index: " + gradeIndex);
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage() + " for student ID: " + studentId);
        }
    }


    /**
     * Removes a student from the list by ID.
     *
     * @param studentId the ID of the student to be removed
     */
    public void removeStudent(int studentId) {
        try {
            boolean removed = studentList.removeStudentById(studentId);
            if (!removed) {
                throw new StudentNotFoundException(studentId);
            } else {
                System.out.println("Student removed.");
            }
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage() + " for student ID: " + studentId);
        }
    }


    /**
     * Removes a grade from a student identified by ID and grade index.
     *
     * @param studentId the ID of the student
     * @param gradeIndex the index of the grade to be removed
     */
    public void removeGradeFromStudent(int studentId, int gradeIndex) {
        try {
            Student student = studentList.findStudentById(studentId);
            if (student != null) {
                if (!student.removeGrade(gradeIndex)) {
                    throw new InvalidGradeIndexException(gradeIndex);
                } else {
                    System.out.println("Grade removed.");
                }
            } else {
                throw new StudentNotFoundException(studentId);
            }
        } catch (InvalidGradeIndexException e) {
            System.err.println(e.getMessage() + " for grade index: " + gradeIndex);
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage() + " for student ID: " + studentId);
        }
    }


    /**
     * Updates the view with the current list of students.
     */
    public void updateView() {
        studentListView.printStudents(studentList.students);
    }

    /**
     * Returns the student list model.
     *
     * @return the StudentList object
     */
    public StudentList getStudentList() {
        return studentList;
    }
}
