package helper;

import exceptions.InvalidTimeException;
import model.Direction;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static constants.VehicleSurveyAnalyserConstants.AVERAGE_WHEEL_BASE;

public class Helper {
    public long getDay(int milliseconds) {
        return TimeUnit.MILLISECONDS.toDays(milliseconds);
    }

    public int getTime(String record) throws InvalidTimeException {
        try {
            return Integer.parseInt(record.substring(1));
        } catch (Exception exception) {
            throw new InvalidTimeException();
        }
    }

    public float getSpeed(int timeInMilliSeconds) {
        long timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(timeInMilliSeconds);
        float speed = (Float.parseFloat(AVERAGE_WHEEL_BASE) / timeInSeconds) * 3600;
        return Float.parseFloat(String.format("%.2f", speed));
    }

    public long getTotalDays(List<Vehicle> vehicles) {
        Vehicle lastVehicle = vehicles.get(vehicles.size() - 1);
        return getDay(lastVehicle.getPassingTimeInMilliSeconds());
    }

    public List<Vehicle> getVehiclesByDay(long day, List<Vehicle> vehicles) {
        return vehicles.stream().filter(vehicle -> getDay(vehicle.getPassingTimeInMilliSeconds()) == day).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.MORNING))
            return vehicles.stream().filter(this::isVehicleMovingInMorning).collect(Collectors.toList());
        else
            return vehicles.stream().filter(this::isVehicleMovingInEvening).collect(Collectors.toList());
    }

    private boolean isVehicleMovingInEvening(Vehicle vehicle) {
        long vehiclePassingHour = getVehiclePassingHourInDay(vehicle);
        return vehiclePassingHour > 18 && vehiclePassingHour <= 24;
    }

    private boolean isVehicleMovingInMorning(Vehicle vehicle) {
        long vehiclePassingHour = getVehiclePassingHourInDay(vehicle);
        return vehiclePassingHour <= 12 && vehiclePassingHour >= 0;
    }

    private long getVehiclePassingHourInDay(Vehicle vehicle) {
        return TimeUnit.MILLISECONDS.toHours(vehicle.getPassingTimeInMilliSeconds()) % 24;
    }

    public int getVehicleCountByDirection(List<Vehicle> vehicles, Direction direction) {
        return (int) vehicles.stream().filter(vehicle -> vehicle.getDirection().equals(direction)).count();
    }
}
