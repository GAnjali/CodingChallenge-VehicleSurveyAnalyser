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

    default void getReportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        output.print("\t\t" + timePeriod);
        if (timePeriod.equals(TimePeriod.MORNING))
            output.print("\t\t\tRough distance between vehicles during Morning (" + helper.getFormattedTime(0) + " to " + helper.getFormattedTime(12) + ")");
        else
            output.print("\t\t\tRough distance between vehicles during Morning (" + helper.getFormattedTime(18) + " to " + helper.getFormattedTime(24) + ")");
        formatReport(vehicles);
    }

    default void getReportPerHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HOUR);
        for (int hour = 0; hour < 24; hour++) {
            System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(hour) + " to " + helper.getFormattedTime(hour + 1));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, hour));
        }
    }

    default void getReportPer15Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_15_MINUTES);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 4; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 15)) + " to " + helper.getFormattedTime(hour, ((half_part * 15) + 15)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 15));
            }
        }
    }

    default void getReportPerHalfAnHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HALF_AN_HOUR);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 2; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 30)) + " to " + helper.getFormattedTime(hour, ((half_part * 30) + 30)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 30));
            }
        }
    }

    default void getReportPer20Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_20_MINUTES);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 3; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 20)) + " to " + helper.getFormattedTime(hour, ((half_part * 20) + 20)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 20));
            }
        }
    }

    void formatReport(List<Vehicle> vehiclesByTimePeriod);
}
