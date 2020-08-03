package persistence;
import java.io.FileWriter;

// Represents data that can be saved to file
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(FileWriter printWriter);
}