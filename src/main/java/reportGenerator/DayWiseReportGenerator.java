package reportGenerator;

import IO.output.Output;
import helper.Helper;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

import java.util.List;

public interface DayWiseReportGenerator extends ReportGenerator {
    Output output = new Output();
    Helper helper = new Helper();

    default void getReport(List<Vehicle> vehicles) {
        for (long day = 0; day < helper.getTotalDays(vehicles); day++) {
            List<Vehicle> vehiclesOnDay = helper.getVehiclesByDay(day, vehicles);
            generateFullDayReport(day, vehiclesOnDay);
        }
    }

    default void generateFullDayReport(long day, List<Vehicle> vehicles) {
        output.print("\n\tFull day report on Day " + (day + 1));
        for (TimePeriod timePeriod : TimePeriod.values()) {
            if (timePeriod.equals(TimePeriod.MORNING) || timePeriod.equals(TimePeriod.EVENING))
                getReportForMorningOrEvening(vehicles, timePeriod);
            else if (timePeriod.equals(TimePeriod.PER_HOUR)) {
                getReportPerHour(vehicles);
            } else if (timePeriod.equals(TimePeriod.PER_HALF_AN_HOUR)) {
                getReportPerHalfAnHour(vehicles);
            } else if (timePeriod.equals(TimePeriod.PER_20_MINUTES)) {
                getReportPer20Minutes(vehicles);
            } else if (timePeriod.equals(TimePeriod.PER_15_MINUTES)) {
                getReportPer15Minutes(vehicles);
            }
        }
    }

    void getReportPer15Minutes(List<Vehicle> vehicles);

    void getReportPer20Minutes(List<Vehicle> vehicles);

    void getReportPerHalfAnHour(List<Vehicle> vehicles);

    void getReportPerHour(List<Vehicle> vehicles);

    void getReportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod);
}
