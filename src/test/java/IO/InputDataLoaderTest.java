package IO;

import exceptions.NoSuchFileFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.DATA_FILE;

public class InputDataLoaderTest {
    private InputDataLoader inputDataLoader;

    @Before
    public void init() {
        inputDataLoader = new InputDataLoader(DATA_FILE);
    }

    @Test
    public void shouldReturnListOfRecordsWhenCalledLoad() throws IOException, NoSuchFileFoundException {
        List<String> records = inputDataLoader.loadData();
        Assert.assertFalse(records.isEmpty());
    }
}
