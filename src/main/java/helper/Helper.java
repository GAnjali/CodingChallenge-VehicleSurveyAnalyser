package helper;

import exceptions.InvalidTimeException;
import model.Direction;
import model.Vehicle;
import reportGenerator.TimePeriod;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static helper.VehicleSurveyAnalyserConstants.AVERAGE_WHEEL_BASE;

public class Helper {
    TimeUtil timeUtil;

    public Helper() {
        timeUtil = new TimeUtil();
    }

    public int getTime(String record) throws InvalidTimeException {
        try {
            if (record == null)
                return 0;
            return Integer.parseInt(record.substring(1));
        } catch (Exception exception) {
            throw new InvalidTimeException();
        }
    }

    public Double getSpeed(int timeInMilliSeconds) {
        double speed = (Double.parseDouble(AVERAGE_WHEEL_BASE) / timeInMilliSeconds) * 1000;
        return Double.parseDouble(String.format("%.02f", speed));
    }

    public long getTotalDays(List<Vehicle> vehicles) {
        Vehicle lastVehicle = vehicles.get(vehicles.size() - 1);
        return lastVehicle.getDay() + 1;
    }

    public List<Vehicle> getVehiclesByDay(long day, List<Vehicle> vehicles) {
        return vehicles.stream().filter(vehicle -> vehicle.getDay() == day).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.MORNING))
            return vehicles.stream().filter(this::isVehicleMovingInMorning).collect(Collectors.toList());
        else
            return vehicles.stream().filter(this::isVehicleMovingInEvening).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, int hour) {
        return vehicles.stream().filter(vehicle -> timeUtil.getHours(vehicle.getPassingTimeInMilliSeconds()) == hour).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, int hour, int minutes) {
        int toMinutes = hour * 60 + minutes;
        int fromMinutes = toMinutes - 30;
        return vehicles.stream().filter(vehicle -> (timeUtil.getHours(vehicle.getPassingTimeInMilliSeconds()) * 60) + timeUtil.getMinutes(vehicle.getPassingTimeInMilliSeconds()) < toMinutes && (timeUtil.getHours(vehicle.getPassingTimeInMilliSeconds()) * 60) + timeUtil.getMinutes(vehicle.getPassingTimeInMilliSeconds()) >= fromMinutes).collect(Collectors.toList());
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
        return timeUtil.getHours(vehicle.getPassingTimeInMilliSeconds());
    }

    public int getVehicleCountByDirection(List<Vehicle> vehicles, Direction direction) {
        return (int) vehicles.stream().filter(vehicle -> vehicle.getDirection().equals(direction)).count();
    }

    public String getFormattedTime(int hour) {
        if (hour == 24)
            hour = 0;
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.of(hour, 0, 0);
        return DATE_TIME_FORMATTER.format(localTime);
    }

    public String getFormattedTime(int hour, int min) {
        if (min == 60) {
            hour = hour + 1;
            min = 0;
        }
        if (hour == 24)
            hour = 0;
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.of(hour, min, 0);
        return DATE_TIME_FORMATTER.format(localTime);
    }

    public List<Vehicle> getVehiclesOnCurrentSpeed(List<Vehicle> vehicles, float speed) {
        return vehicles.stream().filter(vehicle -> vehicle.getSpeed() > speed - 5 && vehicle.getSpeed() <= speed).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByDirection(List<Vehicle> vehicles, Direction direction) {
        return vehicles.stream().filter(vehicle -> vehicle.getDirection().equals(direction)).collect(Collectors.toList());
    }

    public double getAverage(List<Double> interVehicularDistance) {
        OptionalDouble average = interVehicularDistance.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public double getTimeGapAmongVehiclesInSeconds(int firstVehiclePassingTime, int secondVehiclePassingTime) {
        int gapInMilliseconds = firstVehiclePassingTime > secondVehiclePassingTime ? firstVehiclePassingTime - secondVehiclePassingTime : secondVehiclePassingTime - firstVehiclePassingTime;
        return gapInMilliseconds / 1000;
    }
}
