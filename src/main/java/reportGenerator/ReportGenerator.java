package reportGenerator;

import IO.output.Output;
import model.Vehicle;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

import static constants.VehicleSurveyAnalyserConstants.REPORT_FOLDER;

public interface ReportGenerator {
    Output output = new Output();

    default PrintStream getPrintStream(String reportFileName) {
        PrintStream printStream = null;
        try {
            File dir = new File(REPORT_FOLDER);
            dir.mkdirs();
            File tmp = new File(dir, reportFileName);
            if (!tmp.createNewFile()) {
                output.print(String.format("File create failed - %s file may be already existing.", reportFileName));
            }

            OutputStream os = new FileOutputStream(Paths.get(tmp.getPath()).toFile());
            printStream = new PrintStream(os);
        } catch (IOException e) {
            output.print("Could not create Report file." + e);
        }
        return printStream;
    }

    void generate(List<Vehicle> vehicles) throws FileNotFoundException;
}
