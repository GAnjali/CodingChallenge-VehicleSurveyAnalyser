package model;

import java.util.concurrent.TimeUnit;

import static helper.VehicleSurveyAnalyserConstants.TOTAL_HOURS_PER_DAY;
import static helper.VehicleSurveyAnalyserConstants.TOTAL_MINUTES_PER_HOUR;

public class Vehicle {
    private int passingTimeInMilliSeconds;
    private Direction direction;
    private Double speed;
    private int day;

    public Vehicle(Direction direction, int passingTimeInMilliSeconds, Double speed, int day) {
        this.passingTimeInMilliSeconds = passingTimeInMilliSeconds;
        this.speed = speed;
        this.direction = direction;
        this.day = day;
    }

    public boolean isEquals(Direction direction) {
        return this.direction.equals(direction);
    }

    public boolean isEquals(long day) {
        return this.day == day;
    }

    public boolean isMovingInEvening() {
        long vehiclePassingHour = getVehiclePassingHourInDay();
        return vehiclePassingHour > 18 && vehiclePassingHour <= 24;
    }

    public boolean isMovingInMorning() {
        long vehiclePassingHour = getVehiclePassingHourInDay();
        return vehiclePassingHour <= 12 && vehiclePassingHour >= 0;
    }

    private long getVehiclePassingHourInDay() {
        return getHoursByDay(this.passingTimeInMilliSeconds);
    }

    private long getHoursByDay(int passingTimeInMilliSeconds) {
        return TimeUnit.MILLISECONDS.toHours(passingTimeInMilliSeconds) % TOTAL_HOURS_PER_DAY;
    }

    public boolean isMoving(int hour) {
        return getHoursByDay(this.passingTimeInMilliSeconds) == hour;
    }

    public boolean isMoving(int fromMinutes, int toMinutes) {
        long minutes = (getHoursByDay(this.passingTimeInMilliSeconds) * 60) + getMinutesByHour(this.passingTimeInMilliSeconds);
        return minutes < toMinutes && minutes >= fromMinutes;
    }

    private long getMinutesByHour(int passingTimeInMilliSeconds) {
        return TimeUnit.MILLISECONDS.toMinutes(passingTimeInMilliSeconds) % TOTAL_MINUTES_PER_HOUR;
    }

    public boolean isSpeedInBetween(float fromSpeed, float toSpeed) {
        return this.speed > fromSpeed && this.speed <= toSpeed;
    }

    public Double getInterVehicularDistance(Vehicle currentVehicle) {
        double timeGapAmongVehiclesInSeconds = getTimeGapAmongVehiclesInSeconds(this.passingTimeInMilliSeconds, currentVehicle.passingTimeInMilliSeconds);
        double speedGapAmongVehicles = Math.abs(this.speed - currentVehicle.speed);
        return Double.parseDouble(String.format("%.2f", timeGapAmongVehiclesInSeconds * speedGapAmongVehicles / 1000));
    }

    private double getTimeGapAmongVehiclesInSeconds(int firstVehiclePassingTime, int secondVehiclePassingTime) {
        int gapInMilliseconds = firstVehiclePassingTime > secondVehiclePassingTime ? firstVehiclePassingTime - secondVehiclePassingTime : secondVehiclePassingTime - firstVehiclePassingTime;
        return gapInMilliseconds / 1000;
    }

    public double getHighestSpeedAmong(Vehicle vehicle) {
        return this.speed > vehicle.speed ? this.speed : vehicle.speed;
    }
}
