package model;

public class Vehicle {
    private final int passingTimeInMilliSeconds;
    private final Direction direction;
    private float speed;

    public Vehicle(Direction direction, int passingTimeInMilliSeconds, float speed) {
        this.passingTimeInMilliSeconds = passingTimeInMilliSeconds;
        this.speed = speed;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getPassingTimeInMilliSeconds() {
        return passingTimeInMilliSeconds;
    }

    public float getSpeed() {
        return speed;
    }
}
