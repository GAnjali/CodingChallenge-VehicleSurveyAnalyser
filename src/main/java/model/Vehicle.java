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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getPassingTimeInMilliSeconds() {
        return passingTimeInMilliSeconds;
    }

    public void setPassingTimeInMilliSeconds(int passingTimeInMilliSeconds) {
        this.passingTimeInMilliSeconds = passingTimeInMilliSeconds;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public int getDay() {
        return day;
    }
}
