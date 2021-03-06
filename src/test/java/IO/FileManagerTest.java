package IO;

import exceptions.UnableToCreateFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static helper.VehicleSurveyAnalyserConstants.VEHICLE_COUNT_REPORT_FILE_NAME;

public class FileManagerTest {
    private FileManager FileManager;

    @Before
    public void init() {
        FileManager = new FileManager();
    }

    @Test
    public void shouldGetPrintStreamForTheSpecifiedFile() throws UnableToCreateFileException, FileNotFoundException {
        Assert.assertNotNull(FileManager.getPrintStream(VEHICLE_COUNT_REPORT_FILE_NAME));
    }

    @Test(expected = UnableToCreateFileException.class)
    public void shouldThrowUnableToCreateFileExceptionForTheSpecifiedFileName() throws UnableToCreateFileException, FileNotFoundException {
        FileManager.getPrintStream("1234_-\n");
    }
}
