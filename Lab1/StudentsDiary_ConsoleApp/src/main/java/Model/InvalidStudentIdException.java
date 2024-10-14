package Model;

public class InvalidStudentIdException extends Exception {
    public InvalidStudentIdException() {
        super("Id of a student has to be an Integer!");
    }
}
