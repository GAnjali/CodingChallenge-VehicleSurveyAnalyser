package IO;

import exceptions.NoSuchFileFoundException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InputDataLoader {
    private String inputFile;

    public InputDataLoader(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<String> loadData() throws IOException, NoSuchFileFoundException {
        try {
            return Files.readAllLines(getPath(), StandardCharsets.UTF_8);
        } catch (NoSuchFileException exception) {
            throw new NoSuchFileFoundException();
        } catch (Exception exception) {
            throw new IOException();
        }
    }

    private Path getPath() {
        return Paths.get(inputFile);
    }
}
