package IO;

import exceptions.UnableToCreateFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static helper.VehicleSurveyAnalyserConstants.VEHICLE_COUNT_REPORT_FILE_NAME;

public class FileOutputDriverTest {
    private FileOutputDriver FileOutputDriver;

    @Before
    public void init() {
        FileOutputDriver = new FileOutputDriver();
    }

    @Test
    public void shouldGetPrintStreamForTheSpecifiedFile() throws UnableToCreateFileException, FileNotFoundException {
        Assert.assertNotNull(FileOutputDriver.getPrintStream(VEHICLE_COUNT_REPORT_FILE_NAME));
    }

    @Test(expected = UnableToCreateFileException.class)
    public void shouldThrowUnableToCreateFileExceptionForTheSpecifiedFileName() throws UnableToCreateFileException, FileNotFoundException {
        FileOutputDriver.getPrintStream("1234_-\n");
    }
}
