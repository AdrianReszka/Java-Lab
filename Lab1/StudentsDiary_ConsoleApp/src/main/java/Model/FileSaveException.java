package Model;

import java.io.IOException;

/**
 * Exception thrown when an error occurs while saving a student list to a file.
 *
 * @author YourName
 * @version 1.0.2
 */
public class FileSaveException extends IOException {

    /**
     * Constructs a new FileSaveException with a message indicating the file that could not be saved.
     *
     * @param filename the name of the file that failed to save
     */
    public FileSaveException(String filename) {
        super("Error saving student list to file: " + filename);
    }
}
