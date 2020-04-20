package model;

import helper.DayWiseReportGeneratorUtil;

import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.TOTAL_HOURS_PER_DAY;
import static helper.VehicleSurveyAnalyserConstants.TOTAL_MINUTES_PER_HOUR;

public class PeakVolumeTime {
    private int hour;
    private int minutes;
    private DayWiseReportGeneratorUtil util;

    public PeakVolumeTime() {
        util = new DayWiseReportGeneratorUtil();
    }

    public void calculate(List<Vehicle> vehicles, int parts) {
        int peakVolume = 0;
        for (int hour = 0; hour < TOTAL_HOURS_PER_DAY; hour++) {
            for (int half_part = 0; half_part < parts; half_part++) {
                List<Vehicle> vehiclesOnCurrentHour;
                if (parts == 1) vehiclesOnCurrentHour = util.getVehiclesByTimePeriod(vehicles, hour);
                else
                    vehiclesOnCurrentHour = util.getVehiclesByTimePeriod(vehicles, hour, half_part * (TOTAL_MINUTES_PER_HOUR / parts));
                if (vehiclesOnCurrentHour.size() > peakVolume) {
                    this.hour = hour;
                    this.minutes = half_part * (TOTAL_MINUTES_PER_HOUR / parts);
                    peakVolume = vehiclesOnCurrentHour.size();
                }
            }
        }
    }

    public String getFormattedHour(int hourNeedToAdd) {
        return util.getFormattedTime(this.hour + hourNeedToAdd);
    }

    public String getFormattedHourAndMinute(int minutesNeedToAdd) {
        return util.getFormattedTime(this.hour, (this.minutes + minutesNeedToAdd));
    }

    public List<Vehicle> getVehiclesInTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.PER_HOUR)) return util.getVehiclesByTimePeriod(vehicles, this.hour);
        return util.getVehiclesByTimePeriod(vehicles, this.hour, this.minutes);
    }
}
