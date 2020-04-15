package reportGenerator.daywise;

import exceptions.UnableToCreateFileException;
import model.Direction;
import model.Vehicle;
import reportGenerator.TimePeriod;

import java.io.FileNotFoundException;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public class PeakVolumeTimesReportGenerator extends DayWiseReportGenerator {
    private PeakVolumeTime peakVolumeTime;

    public PeakVolumeTimesReportGenerator() {
        peakVolumeTime = new PeakVolumeTime();
    }

    @Override
    public void generate(List<Vehicle> vehicles) throws UnableToCreateFileException, FileNotFoundException {
        writeToFile(PEAK_VOLUME_TIMES_REPORT_FILE_NAME);
        printStream.print(PEAK_VOLUME_TIMES_REPORT_GENERATOR_TEMPLATE);
        getReport(vehicles);
    }

    @Override
    public void reportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.MORNING)) {
            printStream.print(MORNING_VS_EVENING_MESSAGE_TEMPLATE);
            TimePeriod peakTimePeriod = getPeakTimePeriodMorningVsEvening(vehicles);
            if (peakTimePeriod.equals(TimePeriod.MORNING))
                printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(0), helper.getFormattedTime(12)));
            else
                printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(18), helper.getFormattedTime(24)));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, peakTimePeriod));
        }
    }

    private TimePeriod getPeakTimePeriodMorningVsEvening(List<Vehicle> vehicles) {
        int countOfVehiclesInMorning = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.MORNING).size();
        int countOfVehiclesInEvening = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.EVENING).size();
        return countOfVehiclesInMorning > countOfVehiclesInEvening ? TimePeriod.MORNING : TimePeriod.EVENING;
    }

    @Override
    public void reportByPartsOfTimePeriod(List<Vehicle> vehicles, TimePeriod timePeriod) {
        printStream.print(String.format(TIME_PERIOD_MESSAGE_TEMPLATE, timePeriod));
        int minutesOfTimePeriod = getMinutesOfTimePeriod(timePeriod);
        peakVolumeTime.calculate(vehicles, TOTAL_MINUTES_PER_HOUR / minutesOfTimePeriod);
        if (timePeriod.equals(TimePeriod.PER_HOUR)) {
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(peakVolumeTime.getHour()), helper.getFormattedTime(peakVolumeTime.getHour() + 1)));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeTime.getHour()));
        } else {
            printStream.print(String.format(FROM_TIME_TO_TIME_TEMPLATE, helper.getFormattedTime(peakVolumeTime.getHour(), peakVolumeTime.getMinutes()), helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes() + minutesOfTimePeriod))));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeTime.getHour(), peakVolumeTime.getMinutes()));
        }
    }

    @Override
    public void formatReport(List<Vehicle> vehicles) {
        printStream.print("\n" + TOTAL_PEAK_VOLUME_TIMES_MESSAGE_TEMPLATE + vehicles.size());
        printStream.print("\n" + NORTH_VEHICLES_PEAK_VOLUME_TIMES_MESSAGE_TEMPLATE + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        printStream.print("\n" + SOUTH_VEHICLES_PEAK_VOLUME_TIMES_MESSAGE_TEMPLATE + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
