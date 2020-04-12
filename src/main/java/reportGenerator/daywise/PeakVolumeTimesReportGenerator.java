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
        printStream.print(PEAK_VOLUME_TIMES_REPORT_GENERATOR);
        getReport(vehicles);
    }

    @Override
    public void getReportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.MORNING)) {
            printStream.print(MORNING_VS_EVENING_MESSAGE);
            TimePeriod peakTimePeriod = getPeakTimePeriodMorningVsEvening(vehicles);
            if (peakTimePeriod.equals(TimePeriod.MORNING))
                printStream.print("\n\t\tFrom HOUR " + helper.getFormattedTime(0) + " to " + helper.getFormattedTime(12));
            else
                printStream.print("\n\t\tFrom HOUR " + helper.getFormattedTime(18) + " to " + helper.getFormattedTime(24));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, peakTimePeriod));
        }
    }

    private TimePeriod getPeakTimePeriodMorningVsEvening(List<Vehicle> vehicles) {
        int countOfVehiclesInMorning = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.MORNING).size();
        int countOfVehiclesInEvening = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.EVENING).size();
        return countOfVehiclesInMorning > countOfVehiclesInEvening ? TimePeriod.MORNING : TimePeriod.EVENING;
    }

    @Override
    public void getReportPerHour(List<Vehicle> vehicles) {
        printStream.print("\n\t\t" + TimePeriod.PER_HOUR);
        peakVolumeTime.calculate(vehicles, 1);
        int peakVolumeHour = peakVolumeTime.getHour();
        printStream.print("\n\t\tFrom HOUR " + helper.getFormattedTime(peakVolumeHour) + " to " + helper.getFormattedTime(peakVolumeHour + 1));
        formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeHour));
    }

    @Override
    public void getReportPerHalfAnHour(List<Vehicle> vehicles) {
        printStream.print("\n\t\t" + TimePeriod.PER_HALF_AN_HOUR);
        peakVolumeTime.calculate(vehicles, 2);
        printStream.print("\n\t\tFrom " + helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes())) + " to " + helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes() + 30)));
        formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeTime.getHour(), peakVolumeTime.getMinutes()));
    }

    @Override
    public void getReportPer20Minutes(List<Vehicle> vehicles) {
        printStream.print("\n\t\t" + TimePeriod.PER_20_MINUTES);
        peakVolumeTime.calculate(vehicles, 3);
        printStream.print("\n\t\tFrom " + helper.getFormattedTime(peakVolumeTime.getHour(), peakVolumeTime.getMinutes()) + " to " + helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes() + 20)));
        formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeTime.getHour(), peakVolumeTime.getMinutes()));
    }

    @Override
    public void getReportPer15Minutes(List<Vehicle> vehicles) {
        printStream.print("\n\t\t" + TimePeriod.PER_15_MINUTES);
        peakVolumeTime.calculate(vehicles, 4);
        printStream.print("\n\t\tFrom " + helper.getFormattedTime(peakVolumeTime.getHour(), peakVolumeTime.getMinutes()) + " to " + helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes() + 15)));
        formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeTime.getHour(), peakVolumeTime.getMinutes()));
    }

    @Override
    public void formatReport(List<Vehicle> vehicles) {
        printStream.print("\n" + TOTAL_PEAK_VOLUME_TIMES_MESSAGE + vehicles.size());
        printStream.print("\n" + NORTH_VEHICLES_PEAK_VOLUME_TIMES_MESSAGE + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        printStream.print("\n" + SOUTH_VEHICLES_PEAK_VOLUME_TIMES_MESSAGE + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
