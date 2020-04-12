package reportGenerator.daywise;

import exceptions.UnableToCreateFileException;
import helper.Helper;
import model.Vehicle;
import reportGenerator.ReportGenerator;
import reportGenerator.TimePeriod;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public abstract class DayWiseReportGenerator implements ReportGenerator {
    Helper helper = new Helper();
    PrintStream printStream = null;

    public void writeToFile(String file) throws UnableToCreateFileException, FileNotFoundException {
        printStream = getOutputStream(file);
    }

    void getReport(List<Vehicle> vehicles) {
        for (long day = 0; day < helper.getTotalDays(vehicles); day++) {
            List<Vehicle> vehiclesOnDay = helper.getVehiclesByDay(day, vehicles);
            generateFullDayReport(day, vehiclesOnDay);
        }
    }

    void generateFullDayReport(long day, List<Vehicle> vehicles) {
        printStream.print("\n\tFull day report on Day " + (day + 1));
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

    void getReportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        printStream.print("\n\t\t" + timePeriod);
        if (timePeriod.equals(TimePeriod.MORNING))
            printStream.print("\tFrom " + helper.getFormattedTime(0) + " to " + helper.getFormattedTime(12) + "");
        else
            printStream.print("\tFrom " + helper.getFormattedTime(18) + " to " + helper.getFormattedTime(24) + "");
        formatReport(vehicles);
    }

    void getReportPerHour(List<Vehicle> vehicles) {
        printStream.print("\n\t\t" + TimePeriod.PER_HOUR);
        for (int hour = 0; hour < 24; hour++) {
            printStream.print("\n\t\tFrom HOUR " + helper.getFormattedTime(hour) + " to " + helper.getFormattedTime(hour + 1));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, hour));
        }
    }

    void getReportPer15Minutes(List<Vehicle> vehicles) {
        printStream.print("\n\t\t" + TimePeriod.PER_15_MINUTES);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 4; half_part++) {
                printStream.println("\n\t\tFrom " + helper.getFormattedTime(hour, (half_part * 15)) + " to " + helper.getFormattedTime(hour, ((half_part * 15) + 15)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 15));
            }
        }
    }

    void getReportPerHalfAnHour(List<Vehicle> vehicles) {
        printStream.print("\n\t\t" + TimePeriod.PER_HALF_AN_HOUR);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 2; half_part++) {
                printStream.println("\n\t\tFrom " + helper.getFormattedTime(hour, (half_part * 30)) + " to " + helper.getFormattedTime(hour, ((half_part * 30) + 30)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 30));
            }
        }
    }

    void getReportPer20Minutes(List<Vehicle> vehicles) {
        printStream.print("\n\t\t" + TimePeriod.PER_20_MINUTES);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 3; half_part++) {
                printStream.print("\n\t\tFrom " + helper.getFormattedTime(hour, (half_part * 20)) + " to " + helper.getFormattedTime(hour, ((half_part * 20) + 20)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 20));
            }
        }
    }

    abstract void formatReport(List<Vehicle> vehiclesByTimePeriod);
}
