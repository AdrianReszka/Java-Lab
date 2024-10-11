package Model;

import java.io.IOException;

public class FileLoadException extends IOException {
    public FileLoadException(String filename) {
        super("Error loading student list from file: " + filename);
    }
}
