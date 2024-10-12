package Model;

/**
 * Exception thrown when student with specified ID already exists.
 *
 * @author YourName
 * @version 1.0.1
 */

public class StudentAlreadyExistsException extends Exception {

    /**
     * Constructs a new StudentAlreadyExistsException with a message indicating the ID of the student that already exists.
     *
     * @param studentId the ID of the student that already exists
     */
    public StudentAlreadyExistsException(int studentId) {
        super("Student with ID " + studentId + " already exists");
    }
}

