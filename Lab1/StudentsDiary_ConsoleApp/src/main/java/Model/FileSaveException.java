package Model;

import java.io.IOException;

public class FileSaveException extends IOException {
    public FileSaveException(String filename) {
        super("Error saving student list to file: " + filename);
    }
}
