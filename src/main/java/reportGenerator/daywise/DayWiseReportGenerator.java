package reportGenerator.daywise;

import exceptions.UnableToCreateFileException;
import helper.DayWiseReportGeneratorUtil;
import model.Day;
import model.Vehicle;
import reportGenerator.ReportGenerator;
import model.TimePeriod;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public abstract class DayWiseReportGenerator implements ReportGenerator {
    protected PrintStream printStream = null;
    protected DayWiseReportGeneratorUtil util = new DayWiseReportGeneratorUtil();

    public void writeToFile(String file) throws UnableToCreateFileException, FileNotFoundException {
        printStream = getOutputStream(file);
    }

    void getReport(List<Vehicle> vehicles) {
        Day day = Day.MONDAY;
        List<Vehicle> vehicleOnDay;
        while (true) {
            vehicleOnDay = util.getVehiclesByDay(day, vehicles);
            if (vehicleOnDay.size() == 0) break;
            printStream.print(String.format(FULL_DAY_REPORT_MESSAGE_TEMPLATE, Day.valueOfLabel(day.label)));
            getReportDayWise(vehicleOnDay, day);
            day = Day.valueOfLabel(day.label + 1);
        }
    }

    public void getReportDayWise(List<Vehicle> vehicles, Day day) {
        generateFullDayReport(vehicles);
    }

    void generateFullDayReport(List<Vehicle> vehicles) {
        for (TimePeriod timePeriod : TimePeriod.values()) {
            reportByTimePeriod(vehicles, timePeriod);
        }
    }

    private void reportByTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, timePeriod));
        if (timePeriod.equals(TimePeriod.MORNING) || timePeriod.equals(TimePeriod.EVENING))
            reportForMorningOrEvening(util.getVehiclesByTimePeriod(vehicles, timePeriod), timePeriod);
        else reportByPartsOfTimePeriod(vehicles, timePeriod);
    }

    void reportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.MORNING))
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, util.getFormattedTime(0), util.getFormattedTime(12)));
        else
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, util.getFormattedTime(18), util.getFormattedTime(24)));
        formatReport(vehicles);
    }

    void reportByPartsOfTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        int minutesOfTimePeriod = getMinutesOfTimePeriod(timePeriod);
        for (int hour = 0; hour < TOTAL_HOURS_PER_DAY; hour++) {
            for (int part_of_hour = 0; part_of_hour < getPartsByTimePeriod(minutesOfTimePeriod); part_of_hour++) {
                printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, util.getFormattedTime(hour, (part_of_hour * minutesOfTimePeriod)), util.getFormattedTime(hour, ((part_of_hour * minutesOfTimePeriod) + minutesOfTimePeriod))));
                formatReport(getCurrentVehicles(vehicles, hour, part_of_hour, minutesOfTimePeriod));
            }
        }
    }

    private int getPartsByTimePeriod(int minutesOfTimePeriod) {
        return TOTAL_MINUTES_PER_HOUR / minutesOfTimePeriod;
    }

    private List<Vehicle> getCurrentVehicles(List<Vehicle> vehicles, int hour, int half_part, int minutesOfTimePeriod) {
        if (minutesOfTimePeriod == TOTAL_MINUTES_PER_HOUR) return util.getVehiclesByTimePeriod(vehicles, hour);
        return util.getVehiclesByTimePeriod(vehicles, hour, half_part * minutesOfTimePeriod);
    }

    int getMinutesOfTimePeriod(TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.PER_HOUR)) return 60;
        else if (timePeriod.equals(TimePeriod.PER_HALF_AN_HOUR)) return 30;
        else if (timePeriod.equals(TimePeriod.PER_20_MINUTES)) return 20;
        else return 15;
    }

    abstract void formatReport(List<Vehicle> vehiclesByTimePeriod);
}
