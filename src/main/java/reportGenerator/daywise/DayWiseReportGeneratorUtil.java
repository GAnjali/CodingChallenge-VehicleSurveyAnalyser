package reportGenerator.daywise;

import model.Direction;
import model.Vehicle;
import reportGenerator.TimePeriod;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DayWiseReportGeneratorUtil {
    private TimeUtil timeUtil;

    public DayWiseReportGeneratorUtil(){
        timeUtil = new TimeUtil();
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

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, int hour) {
        return vehicles.stream().filter(vehicle -> timeUtil.getHours(vehicle.getPassingTimeInMilliSeconds()) == hour).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, int hour, int minutes) {
        int toMinutes = hour * 60 + minutes;
        int fromMinutes = toMinutes - 30;
        return vehicles.stream().filter(vehicle -> (timeUtil.getHours(vehicle.getPassingTimeInMilliSeconds()) * 60) + timeUtil.getMinutes(vehicle.getPassingTimeInMilliSeconds()) < toMinutes && (timeUtil.getHours(vehicle.getPassingTimeInMilliSeconds()) * 60) + timeUtil.getMinutes(vehicle.getPassingTimeInMilliSeconds()) >= fromMinutes).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByDirection(List<Vehicle> vehicles, Direction direction) {
        return vehicles.stream().filter(vehicle -> vehicle.getDirection().equals(direction)).collect(Collectors.toList());
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
}
