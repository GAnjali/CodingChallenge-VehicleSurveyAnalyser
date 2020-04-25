package model;

public class Sensor {
    private String label;

    public Sensor(String label) {
        this.label = label;
    }

    public boolean isEqual(String label) {
        return this.label.equals(label);
    }
}
