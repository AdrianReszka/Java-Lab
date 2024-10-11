package Model;

/**
 * Exception thrown when an invalid grade format is encountered.
 *
 * @author YourName
 * @version 1.0.1
 */
public class InvalidGradeFormatException extends Exception {

    /**
     * Constructs a new InvalidGradeFormatException with a message indicating the invalid grade input.
     *
     * @param gradeInput the invalid grade input string
     */
    public InvalidGradeFormatException(String gradeInput) {
        super("Invalid grade format: " + gradeInput + ". Please use a valid number with a dot or comma.");
    }
}
