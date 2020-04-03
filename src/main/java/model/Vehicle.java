package model;

public class Vehicle {
    private final int frontAxleTime;
    private final int rearAxleTime;
    private final Direction direction;
    private int day;


    public Vehicle(int frontAxleTime, int rearAxleTime, Direction direction, int day) {
        this.frontAxleTime = frontAxleTime;
        this.rearAxleTime = rearAxleTime;
        this.direction = direction;
        this.day = day;
    }
}
