package model;

import helper.DayWiseReportGeneratorUtil;

import java.util.List;

public class PeakVolumeTime {
    private int hour;
    private int minutes;
    private DayWiseReportGeneratorUtil util;

    public PeakVolumeTime() {
        util = new DayWiseReportGeneratorUtil();
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void calculate(List<Vehicle> vehicles, int parts) {
        int peakVolume = 0;
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < parts; half_part++) {
                List<Vehicle> vehiclesOnCurrentHour;
                if (parts == 1)
                    vehiclesOnCurrentHour = util.getVehiclesByTimePeriod(vehicles, hour);
                else
                    vehiclesOnCurrentHour = util.getVehiclesByTimePeriod(vehicles, hour, half_part * (60 / parts));
                if (vehiclesOnCurrentHour.size() > peakVolume) {
                    this.hour = hour;
                    this.minutes = half_part * (60 / parts);
                    peakVolume = vehiclesOnCurrentHour.size();
                }
            }
        }
    }
}
