package helper;

import model.Day;
import model.Direction;
import model.TimePeriod;
import model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import helper.DayWiseReportGeneratorUtil;

import java.util.ArrayList;
import java.util.List;

public class DayWiseReportGeneratorUtilTest {
    private DayWiseReportGeneratorUtil util;
    private List<Vehicle> vehicles;

    @Before
    public void init() {
        util = new DayWiseReportGeneratorUtil();
        vehicles = new ArrayList<>();
    }

    @Test
    public void shouldReturnListOfVehiclesForGivenVehicleDataWithDay1() {
        vehicles.add(new Vehicle(Direction.SOUTH, 18006091, 5.00, Day.MONDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 64635695, 5.00, Day.TUESDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 90060911, 5.00, Day.WEDNESDAY));
        List<Vehicle> vehiclesOnDay1 = util.getVehiclesByDay(Day.MONDAY, vehicles);
        Assert.assertTrue(vehiclesOnDay1.contains(vehicles.get(0)));
    }

    @Test
    public void shouldReturnListOfVehiclesForGivenVehicleDataWithDay7() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5.00, Day.MONDAY));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5.00, Day.TUESDAY));
        vehicles.add(new Vehicle(Direction.NORTH, 646356956, 5.00, Day.TUESDAY));
        List<Vehicle> vehiclesOnDay1 = util.getVehiclesByDay(Day.TUESDAY, vehicles);
        Assert.assertTrue(vehiclesOnDay1.contains(vehicles.get(2)));
    }

    @Test
    public void shouldReturnListOfVehiclesForGivenVehicleDataWithTimePeriodMorning() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5.00, Day.TUESDAY));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.NORTH, 776356956, 5.00, Day.THURSDAY));
        List<Vehicle> vehiclesMovingInMorning = util.getVehiclesByTimePeriod(vehicles, TimePeriod.MORNING);
        Assert.assertTrue(vehiclesMovingInMorning.contains(vehicles.get(0)));
    }

    @Test
    public void shouldReturnListOfVehiclesForGivenVehicleDataWithTimePeriodEvening() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.NORTH, 776356956, 5.00, Day.THURSDAY));
        List<Vehicle> vehiclesMovingInMorning = util.getVehiclesByTimePeriod(vehicles, TimePeriod.EVENING);
        Assert.assertTrue(vehiclesMovingInMorning.contains(vehicles.get(2)));
    }

    @Test
    public void shouldReturnVehiclesByTimePeriodWithGivenHour() {
        vehicles.add(new Vehicle(Direction.SOUTH, 18006091, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 64635695, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 90060911, 5.00, Day.THURSDAY));
        List<Vehicle> vehiclesByTimePeriod = util.getVehiclesByTimePeriod(vehicles, 17);
        Assert.assertTrue(vehiclesByTimePeriod.contains(vehicles.get(1)));
    }

    @Test
    public void shouldReturnVehiclesByTimePeriodWithGivenHourGivenMinutes() {
        vehicles.add(new Vehicle(Direction.SOUTH, 18006091, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 64635695, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 90060911, 5.00, Day.THURSDAY));
        List<Vehicle> vehiclesByTimePeriod = util.getVehiclesByTimePeriod(vehicles, 1, 30);
        Assert.assertTrue(vehiclesByTimePeriod.contains(vehicles.get(2)));
    }

    @Test
    public void shouldReturnVehiclesByTimePeriodWithGivenHourGivenMinutes2() {
        vehicles.add(new Vehicle(Direction.SOUTH, 18006091, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 64635695, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 90060911, 5.00, Day.THURSDAY));
        List<Vehicle> vehiclesByTimePeriod = util.getVehiclesByTimePeriod(vehicles, 5, 30);
        Assert.assertTrue(vehiclesByTimePeriod.contains(vehicles.get(0)));
    }

    @Test
    public void shouldReturnCountOfVehiclesForGivenVehicleDataWithDirectionSouth() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 776356956, 5.00, Day.THURSDAY));
        List<Vehicle> vehiclesMovingTowardsSouth = util.getVehiclesByDirection(vehicles, Direction.SOUTH);
        Assert.assertTrue(vehiclesMovingTowardsSouth.contains(vehicles.get(2)));
    }

    @Test
    public void shouldReturnCountOfVehiclesForGivenVehicleDataWithDirectionNorth() {
        vehicles.add(new Vehicle(Direction.NORTH, 99060186, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.NORTH, 560601863, 5.00, Day.THURSDAY));
        vehicles.add(new Vehicle(Direction.SOUTH, 776356956, 5.00, Day.THURSDAY));
        List<Vehicle> vehiclesMovingTowardsNorth = util.getVehiclesByDirection(vehicles, Direction.NORTH);
        Assert.assertTrue(vehiclesMovingTowardsNorth.contains(vehicles.get(1)));
    }

    @Test
    public void shouldReturnFormattedTimeForGivenHour() {
        Assert.assertEquals("01:00:00", util.getFormattedTime(1));
    }

    @Test
    public void shouldReturnFormattedTimeForGivenHour24() {
        Assert.assertEquals("00:00:00", util.getFormattedTime(24));
    }

    @Test
    public void shouldReturnFormattedTimeForGivenHourAndMinutes() {
        Assert.assertEquals("01:30:00", util.getFormattedTime(1, 30));
    }

    @Test
    public void shouldReturnFormattedTimeForGivenHour23Minutes60() {
        Assert.assertEquals("00:00:00", util.getFormattedTime(23, 60));
    }
}
