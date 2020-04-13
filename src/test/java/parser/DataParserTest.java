package parser;

import exceptions.InvalidDataException;
import exceptions.InvalidTimeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DataParserTest {
    private DataParser dataParser;
    private List<String> records;

    @Before
    public void init() {
        dataParser = new DataParser();
    }

    @Test
    public void shouldReturnCountOfVehiclesWhenCalledGetVehicleCountWithGivenRecords() throws InvalidDataException, InvalidTimeException {
        records = Arrays.asList("A98186", "A98333", "A499718", "A499886", "A638379", "B638382", "A638520", "B638523");
        int count = dataParser.parse(records).size();
        Assert.assertEquals(3, count);
    }

    @Test
    public void shouldReturnCountOfVehiclesWhenCalledGetVehicleCountWithGivenSetRecords() throws InvalidDataException, InvalidTimeException {
        records = Arrays.asList("A98186", "A98333", "A499718", "A499886", "A638379", "B638382", "A638520", "B638523", "A1016488", "A1016648", "A1058535", "B1058538", "A1058659", "B1058662");
        Assert.assertEquals(5, dataParser.parse(records).size());
    }

    @Test(expected = InvalidDataException.class)
    public void shouldThrowAnInvalidDataExceptionWhenCalledGetVehicleCountGivenWithInvalidData() throws InvalidDataException, InvalidTimeException {
        records = Arrays.asList("A98186", "A98333", "A499718", "A499886", "B638379", "B638382", "A638520", "B638523", "A1016488", "A1016648", "A1058535", "B1058538", "A1058659", "B1058662");
        dataParser.parse(records);
    }

    @Test(expected = InvalidTimeException.class)
    public void shouldThrowAnInvalidTimeExceptionWhenCalledGetVehicleCountGivenWithInvalidTimeData() throws InvalidDataException, InvalidTimeException {
        records = Arrays.asList("A98.186", "A98333", "A499718", "A499886", "B638379", "B638382", "A638520", "B638523", "A1016488", "A1016648", "A1058535", "B1058538", "A1058659", "B1058662");
        dataParser.parse(records);
    }
}
