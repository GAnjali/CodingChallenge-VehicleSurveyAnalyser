package IO;

import model.Direction;
import reportGenerator.daywise.PeakVolumeTime;
import model.TimePeriod;
import model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PeakVolumeTimeTest {
    private PeakVolumeTime peakVolumeTime;
    private List<Vehicle> vehicles;

    @Before
    public void init() {
        peakVolumeTime = new PeakVolumeTime();
        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(Direction.SOUTH, 18006091, 5.00, 0));
        vehicles.add(new Vehicle(Direction.SOUTH, 64635695, 5.00, 1));
        vehicles.add(new Vehicle(Direction.SOUTH, 90060911, 5.00, 2));
        peakVolumeTime.calculate(vehicles, 1);
    }

    @Test
    public void shouldReturnGetFormattedHour() {
        Assert.assertEquals("01:00:00", peakVolumeTime.getFormattedHour(0));
    }

    @Test
    public void shouldReturnGetFormattedHourAndMinutes() {
        Assert.assertEquals("01:30:00", peakVolumeTime.getFormattedHourAndMinute(30));
    }

    @Test
    public void shouldContains2ndVehicleInVehiclesInTimePeriodPerHour() {
        Assert.assertTrue(peakVolumeTime.getVehiclesInTimePeriod(vehicles, TimePeriod.PER_HOUR).contains(vehicles.get(2)));
    }
}
