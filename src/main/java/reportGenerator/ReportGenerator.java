package reportGenerator;

import IO.FileManager;
import exceptions.UnableToCreateFileException;
import model.Vehicle;

import java.io.*;
import java.util.List;

public interface ReportGenerator {
    default PrintStream getOutputStream(String reportFileName) throws UnableToCreateFileException, FileNotFoundException {
        FileManager output = new FileManager();
        return output.getPrintStream(reportFileName);
    }

    void generate(List<Vehicle> vehicles) throws FileNotFoundException, UnableToCreateFileException;
}
