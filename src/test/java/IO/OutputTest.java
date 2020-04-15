package IO;

import exceptions.UnableToCreateFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static helper.VehicleSurveyAnalyserConstants.VEHICLE_COUNT_REPORT_FILE_NAME;

public class OutputTest {
    private Output output;

    @Before
    public void init() {
        output = new Output();
    }

    @Test
    public void shouldGetPrintStreamForTheSpecifiedFile() throws UnableToCreateFileException, FileNotFoundException {
        Assert.assertNotNull(output.getPrintStream(VEHICLE_COUNT_REPORT_FILE_NAME));
    }

    @Test(expected = UnableToCreateFileException.class)
    public void shouldThrowUnableToCreateFileExceptionForTheSpecifiedFileName() throws UnableToCreateFileException, FileNotFoundException {
        output.getPrintStream("1234_-\n");
    }
}
