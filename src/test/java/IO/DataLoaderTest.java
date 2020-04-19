package IO;

import exceptions.NoSuchFileFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.DATA_FILE;

public class DataLoaderTest {
    private DataLoader dataLoader;

    @Before
    public void init() {
        dataLoader = new DataLoader(DATA_FILE);
    }

    @Test
    public void shouldReturnListOfRecordsWhenCalledLoad() throws IOException, NoSuchFileFoundException {
        List<String> records = dataLoader.loadData();
        Assert.assertFalse(records.isEmpty());
    }
}
