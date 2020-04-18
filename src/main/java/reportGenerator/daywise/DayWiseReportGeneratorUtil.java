package reportGenerator.daywise;

import model.Direction;
import model.Vehicle;
import reportGenerator.TimePeriod;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DayWiseReportGeneratorUtil {
    public long getTotalDays(List<Vehicle> vehicles) {
        Vehicle lastVehicle = vehicles.get(vehicles.size() - 1);
        return lastVehicle.getDay();
    }

    public List<Vehicle> getVehiclesByDay(long day, List<Vehicle> vehicles) {
        return vehicles.stream().filter(vehicle -> vehicle.isInThisDay(day)).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.MORNING))
            return vehicles.stream().filter(Vehicle::isVehicleMovingInMorning).collect(Collectors.toList());
        else return vehicles.stream().filter(Vehicle::isVehicleMovingInEvening).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, int hour) {
        return vehicles.stream().filter(vehicle -> vehicle.isMovingInThisHour(hour)).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByTimePeriod(List<Vehicle> vehicles, int hour, int minutes) {
        int toMinutes = hour * 60 + minutes;
        int fromMinutes = toMinutes - 30;
        return vehicles.stream().filter(vehicle -> vehicle.isMovingInTheseMinutes(fromMinutes, toMinutes)).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByDirection(List<Vehicle> vehicles, Direction direction) {
        return vehicles.stream().filter(vehicle -> vehicle.isInThisDirection(direction)).collect(Collectors.toList());
    }

    public String getFormattedTime(int hour) {
        if (hour == 24) hour = 0;
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.of(hour, 0, 0);
        return DATE_TIME_FORMATTER.format(localTime);
    }

    public String getFormattedTime(int hour, int min) {
        if (min == 60) {
            hour = hour + 1;
            min = 0;
        }
        if (hour == 24) hour = 0;
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.of(hour, min, 0);
        return DATE_TIME_FORMATTER.format(localTime);
    }
}
