package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VehicleTest {
    private Vehicle vehicle;

    @Before
    public void init() {
        vehicle = new Vehicle(Direction.SOUTH, 18006091, 5.00, 0);
    }

    @Test
    public void shouldReturnTrueForIsEqualsOfGivenDirection() {
        Assert.assertTrue(vehicle.isEquals(Direction.SOUTH));
    }

    @Test
    public void shouldReturnFalseForIsEqualsOfGivenDirection() {
        Assert.assertFalse(vehicle.isEquals(Direction.NORTH));
    }

    @Test
    public void shouldReturnTrueForIsEqualsOfGivenDay() {
        Assert.assertTrue(vehicle.isEquals(0));
    }

    @Test
    public void shouldReturnFalseForIsEqualsOfGivenDay() {
        Assert.assertFalse(vehicle.isEquals(1));
    }

    @Test
    public void shouldReturnTrueWhenCalledIsVehicleMovingInMorning() {
        Assert.assertTrue(vehicle.isMovingInMorning());
    }

    @Test
    public void shouldReturnFalseWhenCalledIsVehicleMovingInEvening() {
        Assert.assertFalse(vehicle.isMovingInEvening());
    }

    @Test
    public void shouldReturnTrueWhenCalledIsMovingInGivenHour() {
        Assert.assertTrue(vehicle.isMoving(5));
    }

    @Test
    public void shouldReturnFalseWhenCalledIsMovingInGivenHour() {
        Assert.assertTrue(vehicle.isMoving(4));
    }

    @Test
    public void shouldReturnTrueWhenCalledIsMovingInGivenHourAndMinutes() {
        Assert.assertTrue(vehicle.isMoving(300, 330));
    }

    @Test
    public void shouldReturnFalseWhenCalledIsMovingInGivenHourAndMinutes() {
        Assert.assertTrue(vehicle.isMoving(4, 0));
    }

    @Test
    public void shouldReturnTrueWhenCalledIsSpeedInBetweenForGivenSpeed() {
        Assert.assertTrue(vehicle.isSpeedInBetween(0.0f, 5.0f));
    }

    @Test
    public void shouldReturnFalseWhenCalledIsSpeedInBetweenForGivenSpeed() {
        Assert.assertTrue(vehicle.isSpeedInBetween(5.0f, 10.0f));
    }

    @Test
    public void shouldReturnInterVehicularDistanceWithGivenVehicle() {
        Vehicle toVehicle = new Vehicle(Direction.SOUTH, 18106091, 10.00, 0);
        Assert.assertEquals(0.5, vehicle.getInterVehicularDistance(toVehicle), 0.0);
    }

    @Test
    public void shouldReturnInterVehicularDistanceViceVersa() {
        Vehicle toVehicle = new Vehicle(Direction.SOUTH, 18106091, 10.00, 0);
        Assert.assertEquals(0.5, toVehicle.getInterVehicularDistance(vehicle), 0.0);
    }

    @Test
    public void shouldReturnHighSpeedAmongVehicles() {
        Vehicle toVehicle = new Vehicle(Direction.SOUTH, 18106091, 10.00, 0);
        Assert.assertEquals(10.0, vehicle.getHighestSpeedAmong(toVehicle), 0.0);
    }
}
