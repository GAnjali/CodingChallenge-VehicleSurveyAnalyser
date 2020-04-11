package reportGenerator;

public class PeakVolumeTime {
    private final int hour;
    private final int minutes;

    public PeakVolumeTime(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }
}
