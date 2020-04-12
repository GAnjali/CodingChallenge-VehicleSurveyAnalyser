package IO.input;

import exceptions.UnableToCreateFileException;

import java.io.*;
import java.nio.file.Paths;

import static helper.VehicleSurveyAnalyserConstants.REPORT_FOLDER;

public class Output {
    public PrintStream getPrintStream(String fileName) throws UnableToCreateFileException, FileNotFoundException {
        File file = createFile(fileName);
        OutputStream os = new FileOutputStream(Paths.get(file.getPath()).toFile());
        return new PrintStream(os);
    }

    private File createFile(String fileName) throws UnableToCreateFileException {
        try {
            File file = new File(getReportDirectory(), fileName);
            file.createNewFile();
            return file;
        } catch (Exception e) {
            throw new UnableToCreateFileException(e);
        }
    }

    private File getReportDirectory() {
        File directory = new File(REPORT_FOLDER);
        directory.mkdirs();
        return directory;
    }
}
