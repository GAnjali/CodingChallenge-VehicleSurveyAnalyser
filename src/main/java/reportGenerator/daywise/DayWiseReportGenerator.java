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
            generateFullDayReport(day, helper.getVehiclesByDay(day, vehicles));
        }
    }

    void generateFullDayReport(long day, List<Vehicle> vehicles) {
        printStream.print(String.format(FULL_DAY_REPORT_MESSAGE_TEMPLATE, (day + 1), vehicles.size()));
        for (TimePeriod timePeriod : TimePeriod.values()) {
            reportByTimePeriod(vehicles, timePeriod);
        }
    }

    private void reportByTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.MORNING) || timePeriod.equals(TimePeriod.EVENING))
            reportForMorningOrEvening(helper.getVehiclesByTimePeriod(vehicles, timePeriod), timePeriod);
        else
            reportByPartsOfTimePeriod(vehicles, timePeriod);
    }

    void reportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, timePeriod));
        if (timePeriod.equals(TimePeriod.MORNING))
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(0), helper.getFormattedTime(12)));
        else
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(18), helper.getFormattedTime(24)));
        formatReport(vehicles);
    }

    void reportByPartsOfTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, timePeriod));
        int minutesOfTimePeriod = getMinutesOfTimePeriod(timePeriod);
        for (int hour = 0; hour < TOTAL_HOURS_PER_DAY; hour++) {
            for (int half_part = 0; half_part < TOTAL_MINUTES_PER_HOUR / minutesOfTimePeriod; half_part++) {
                printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(hour, (half_part * minutesOfTimePeriod)), helper.getFormattedTime(hour, ((half_part * minutesOfTimePeriod) + minutesOfTimePeriod))));
                formatReport(getCurrentVehicles(vehicles, hour, half_part, minutesOfTimePeriod));
            }
        }
    }

    private List<Vehicle> getCurrentVehicles(List<Vehicle> vehicles, int hour, int half_part, int minutesOfTimePeriod) {
        if (minutesOfTimePeriod == TOTAL_MINUTES_PER_HOUR)
            return helper.getVehiclesByTimePeriod(vehicles, hour);
        return helper.getVehiclesByTimePeriod(vehicles, hour, half_part * minutesOfTimePeriod);
    }

    int getMinutesOfTimePeriod(TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.PER_HOUR))
            return 60;
        else if (timePeriod.equals(TimePeriod.PER_HALF_AN_HOUR))
            return 30;
        else if (timePeriod.equals(TimePeriod.PER_20_MINUTES))
            return 20;
        else
            return 15;
    }

    abstract void formatReport(List<Vehicle> vehiclesByTimePeriod);
}
