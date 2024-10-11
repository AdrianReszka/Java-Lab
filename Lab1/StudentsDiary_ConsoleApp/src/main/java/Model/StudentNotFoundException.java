package Model;

/**
 * Exception thrown when a student with a specific ID cannot be found in the student list.
 *
 * @author YourName
 * @version 1.0.1
 */
public class StudentNotFoundException extends Exception {

    /**
     * Constructs a new StudentNotFoundException with a message indicating the ID of the student that was not found.
     *
     * @param studentId the ID of the student that could not be found
     */
    public StudentNotFoundException(int studentId) {
        super("Student with ID " + studentId + " not found.");
    }
}
