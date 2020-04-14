package reportGenerator.daywise;

import exceptions.UnableToCreateFileException;
import helper.Helper;
import model.Vehicle;
import reportGenerator.ReportGenerator;
import reportGenerator.TimePeriod;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

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
        printStream.print(String.format(FULL_DAY_REPORT_MESSAGE_TEMPLATE, (day + 1)));
        for (TimePeriod timePeriod : TimePeriod.values()) {
            if (timePeriod.equals(TimePeriod.MORNING) || timePeriod.equals(TimePeriod.EVENING))
                getReportForMorningOrEvening(helper.getVehiclesByTimePeriod(vehicles, timePeriod), timePeriod);
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
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, timePeriod));
        if (timePeriod.equals(TimePeriod.MORNING))
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(0), helper.getFormattedTime(12)));
        else
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(18), helper.getFormattedTime(24)));
        formatReport(vehicles);
    }

    void getReportPerHour(List<Vehicle> vehicles) {
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, TimePeriod.PER_HOUR));
        for (int hour = 0; hour < 24; hour++) {
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(hour), helper.getFormattedTime(hour + 1)));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, hour));
        }
    }

    void getReportPer15Minutes(List<Vehicle> vehicles) {
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, TimePeriod.PER_15_MINUTES));
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 4; half_part++) {
                printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(hour, (half_part * 15)), helper.getFormattedTime(hour, ((half_part * 15) + 15))));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 15));
            }
        }
    }

    void getReportPerHalfAnHour(List<Vehicle> vehicles) {
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, TimePeriod.PER_HALF_AN_HOUR));
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 2; half_part++) {
                printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(hour, (half_part * 30)), helper.getFormattedTime(hour, ((half_part * 30) + 30))));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 30));
            }
        }
    }

    void getReportPer20Minutes(List<Vehicle> vehicles) {
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, TimePeriod.PER_20_MINUTES));
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 3; half_part++) {
                printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(hour, (half_part * 20)), helper.getFormattedTime(hour, ((half_part * 20) + 20))));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 20));
            }
        }
    }

    abstract void formatReport(List<Vehicle> vehiclesByTimePeriod);
}
