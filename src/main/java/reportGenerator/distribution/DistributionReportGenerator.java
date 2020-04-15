package reportGenerator.distribution;

import exceptions.UnableToCreateFileException;
import helper.Helper;
import reportGenerator.ReportGenerator;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public abstract class DistributionReportGenerator implements ReportGenerator {
    Helper helper = new Helper();
    PrintStream printStream = null;

    public void writeToFile(String file) throws UnableToCreateFileException, FileNotFoundException {
        printStream = getOutputStream(file);
    }
}
