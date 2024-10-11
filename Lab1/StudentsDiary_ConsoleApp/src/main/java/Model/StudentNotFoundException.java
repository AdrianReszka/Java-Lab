package Model;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(int studentId) {
        super("Student with ID " + studentId + " not found.");
    }
}
