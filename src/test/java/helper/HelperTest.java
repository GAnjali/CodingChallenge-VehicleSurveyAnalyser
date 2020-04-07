package helper;

import exceptions.InvalidTimeException;
import model.Direction;
import model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import reportGenerator.timeperiod.TimePeriod;

import java.util.ArrayList;
import java.util.List;

public class HelperTest {
    private Helper helper;
    private List<Vehicle> vehicles;

    @Before
    public void init() {
        helper = new Helper();
        vehicles = new ArrayList<>();
    }

    @Test
    public void shouldReturnDay1ForGivenMilliseconds98186() {
        Assert.assertEquals(1, helper.getDay(90060911));
    }

    @Test
    public void shouldReturnDay2ForGivenMilliseconds5060911() {
        Assert.assertEquals(2, helper.getDay(180060911));
    }

    @Test
    public void shouldReturnDayForGivenMilliseconds499718() {
        Assert.assertEquals(7, helper.getDay(646356956));
    }


    @Test
    public void shouldReturnTimeForGivenRecordA98186() throws InvalidTimeException {
        Assert.assertEquals(98186, helper.getTime("A98186"));
    }

    @Test
    public void shouldReturnTimeForGivenRecordA5060911() throws InvalidTimeException {
        Assert.assertEquals(5060911, helper.getTime("A5060911"));
    }

    @Test(expected = InvalidTimeException.class)
    public void shouldReturnInvalidTimeExceptionForGivenRecord() throws InvalidTimeException {
        helper.getTime("A50.60911");
    }

    @Test
    public void shouldReturnDays1ForGivenVehicleDataWith56060186MilliSeconds() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5));
        Assert.assertEquals(1, helper.getTotalDays(vehicles));
    }

    @Test
    public void shouldReturnDays6ForGivenVehicleDataWith560601863MilliSeconds() {
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5));
        Assert.assertEquals(6, helper.getTotalDays(vehicles));
    }

    @Test
    public void shouldReturnDays7ForGivenVehicleDataWith646356956MilliSeconds() {
        vehicles.add(new Vehicle(Direction.NORTH, 646356956, 5));
        Assert.assertEquals(7, helper.getTotalDays(vehicles));
    }

    @Test
    public void shouldReturnListOfVehiclesForGivenVehicleDataWithDay1() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 646356956, 5));
        List<Vehicle> vehiclesOnDay1 = helper.getVehiclesByDay(1, vehicles);
        Assert.assertTrue(vehiclesOnDay1.contains(vehicles.get(0)));
    }

    @Test
    public void shouldReturnListOfVehiclesForGivenVehicleDataWithDay7() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 646356956, 5));
        List<Vehicle> vehiclesOnDay1 = helper.getVehiclesByDay(7, vehicles);
        Assert.assertTrue(vehiclesOnDay1.contains(vehicles.get(2)));
    }

    @Test
    public void shouldReturnListOfVehiclesForGivenVehicleDataWithTimePeriodMorning() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 776356956, 5));
        List<Vehicle> vehiclesMovingInMorning = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.MORNING);
        Assert.assertTrue(vehiclesMovingInMorning.contains(vehicles.get(0)));
    }

    @Test
    public void shouldReturnListOfVehiclesForGivenVehicleDataWithTimePeriodEvening() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 776356956, 5));
        List<Vehicle> vehiclesMovingInMorning = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.EVENING);
        Assert.assertTrue(vehiclesMovingInMorning.contains(vehicles.get(2)));
    }

    @Test
    public void shouldReturnCountOfVehiclesForGivenVehicleDataWithDirectionSouth() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5));
        vehicles.add(new Vehicle(Direction.SOUTH, 776356956, 5));
        int countOfVehiclesMovingTowardsSouth = helper.getVehicleCountByDirection(vehicles, Direction.SOUTH);
        Assert.assertEquals(1, countOfVehiclesMovingTowardsSouth);
    }

    @Test
    public void shouldReturnCountOfVehiclesForGivenVehicleDataWithDirectionNorth() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5));
        vehicles.add(new Vehicle(Direction.SOUTH, 776356956, 5));
        int countOfVehiclesMovingTowardsSouth = helper.getVehicleCountByDirection(vehicles, Direction.NORTH);
        Assert.assertEquals(2, countOfVehiclesMovingTowardsSouth);
    }
}
