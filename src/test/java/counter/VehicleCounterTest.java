package counter;

import exceptions.InvalidDataException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class VehicleCounterTest {
    private VehicleCounter vehicleCounter;
    private List<String> records;

    @Before
    public void init() {
        vehicleCounter = new VehicleCounter();
    }

    @Test
    public void shouldReturnCountOfVehiclesWhenCalledGetVehicleCountWithGivenRecords() throws InvalidDataException {
        records = Arrays.asList("A98186", "A98333", "A499718", "A499886", "A638379", "B638382", "A638520", "B638523");
        Assert.assertEquals(3, vehicleCounter.getVehiclesCount(records));
    }

    @Test
    public void shouldReturnCountOfVehiclesWhenCalledGetVehicleCountWithGivenSetRecords() throws InvalidDataException {
        records = Arrays.asList("A98186", "A98333", "A499718", "A499886", "A638379", "B638382", "A638520", "B638523", "A1016488", "A1016648", "A1058535", "B1058538", "A1058659", "B1058662");
        Assert.assertEquals(5, vehicleCounter.getVehiclesCount(records));
    }

    @Test(expected = InvalidDataException.class)
    public void shouldThrowAnExceptionWhenCalledGetVehicleCountGivenWithInvalidData() throws InvalidDataException {
        records = Arrays.asList("A98186", "A98333", "A499718", "A499886", "B638379", "B638382", "A638520", "B638523", "A1016488", "A1016648", "A1058535", "B1058538", "A1058659", "B1058662");
        vehicleCounter.getVehiclesCount(records);
    }
}
