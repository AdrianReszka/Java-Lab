package Model;

import java.io.IOException;

/**
 * Exception thrown when an error occurs while loading a student list from a file.
 *
 * @author YourName
 * @version 1.0.2
 */
public class FileLoadException extends IOException {

    /**
     * Constructs a new FileLoadException with a message indicating the file that could not be loaded.
     *
     * @param filename the name of the file that failed to load
     */
    public FileLoadException(String filename) {
        super("Error loading student list from file: " + filename);
    }
}
