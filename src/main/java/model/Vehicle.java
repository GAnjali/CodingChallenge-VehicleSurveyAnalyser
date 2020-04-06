package model;

public class Vehicle {
    private final int passingTime;
    private final Direction direction;
    private float speed;

    public Vehicle(Direction direction, int passingTime, float speed) {
        this.passingTime = passingTime;
        this.speed = speed;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getPassingTime() {
        return passingTime;
    }

    public float getSpeed() {
        return speed;
    }
}
