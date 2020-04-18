package reportGenerator;

import IO.FileOutputDriver;
import exceptions.UnableToCreateFileException;
import model.Vehicle;

import java.io.*;
import java.util.List;

public interface ReportGenerator {
    default PrintStream getOutputStream(String reportFileName) throws UnableToCreateFileException, FileNotFoundException {
        FileOutputDriver output = new FileOutputDriver();
        return output.getPrintStream(reportFileName);
    }

    void generate(List<Vehicle> vehicles) throws FileNotFoundException, UnableToCreateFileException;
}
