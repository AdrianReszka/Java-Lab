package Model;

public class InvalidGradeFormatException extends Exception {
    public InvalidGradeFormatException(String gradeInput) {
        super("Invalid grade format: " + gradeInput + ". Please use a valid number with a dot or comma.");
    }
}
