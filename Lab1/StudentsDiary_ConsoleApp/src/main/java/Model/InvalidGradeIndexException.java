package Model;

public class InvalidGradeIndexException extends Exception {
    public InvalidGradeIndexException(int index) {
        super("Invalid grade index: " + (index + 1));
    }
}
