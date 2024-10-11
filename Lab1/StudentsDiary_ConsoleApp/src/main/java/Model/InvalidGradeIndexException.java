package Model;

/**
 * Exception thrown when an invalid index is provided for accessing a student's grades.
 *
 * @author YourName
 * @version 1.0.0
 */
public class InvalidGradeIndexException extends Exception {

    /**
     * Constructs a new InvalidGradeIndexException with a message indicating the invalid index.
     *
     * @param index the invalid grade index
     */
    public InvalidGradeIndexException(int index) {
        super("Invalid grade index: " + (index + 1));
    }
}
