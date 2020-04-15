package model;

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

    public Direction getDirection() {
        return direction;
    }

    public int getPassingTimeInMilliSeconds() {
        return passingTimeInMilliSeconds;
    }

    public Double getSpeed() {
        return speed;
    }

    public int getDay() {
        return day;
    }
}
