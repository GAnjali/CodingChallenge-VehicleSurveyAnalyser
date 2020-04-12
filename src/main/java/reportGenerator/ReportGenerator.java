package reportGenerator;

import exceptions.InvalidFileCreationException;
import model.Vehicle;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

import static constants.VehicleSurveyAnalyserConstants.REPORT_FOLDER;

public interface ReportGenerator {
    default PrintStream getPrintStream(String reportFileName) throws InvalidFileCreationException {
        PrintStream printStream = null;
        try {
            File dir = new File(REPORT_FOLDER);
            dir.mkdirs();
            File tmp = new File(dir, reportFileName);
            tmp.createNewFile();
            OutputStream os = new FileOutputStream(Paths.get(tmp.getPath()).toFile());
            printStream = new PrintStream(os);
        } catch (IOException e) {
            throw new InvalidFileCreationException();
        }
        return printStream;
    }

    void generate(List<Vehicle> vehicles) throws FileNotFoundException, InvalidFileCreationException;
}
