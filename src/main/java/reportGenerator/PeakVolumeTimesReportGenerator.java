package reportGenerator;

import model.Direction;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

import java.util.List;

public class PeakVolumeTimesReportGenerator implements DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) {
        output.print("***************Peak Volume Times Report***************");
        getReport(vehicles);
    }

    @Override
    public void getReportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if (timePeriod.equals(TimePeriod.MORNING)) {
            output.print("\n\t\tMorning Vs Evening:");
            TimePeriod peakTimePeriod = getPeakTimePeriodMorningVsEvening(vehicles);
            if (peakTimePeriod.equals(TimePeriod.MORNING))
                System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(0) + " to " + helper.getFormattedTime(12));
            else
                System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(18) + " to " + helper.getFormattedTime(24));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, peakTimePeriod));
        }
    }

    private TimePeriod getPeakTimePeriodMorningVsEvening(List<Vehicle> vehicles) {
        int countOfVehiclesInMorning = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.MORNING).size();
        int countOfVehiclesInEvening = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.EVENING).size();
        return countOfVehiclesInMorning > countOfVehiclesInEvening ? TimePeriod.MORNING : TimePeriod.EVENING;
    }

    public void getReportPerHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HOUR);
        PeakVolumeTime peakVolumeTime = getPeakVolumeHour(vehicles);
        int peakVolumeHour = peakVolumeTime.getHour();
        System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(peakVolumeHour) + " to " + helper.getFormattedTime(peakVolumeHour + 1));
        formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeHour));
    }

    private PeakVolumeTime getPeakVolumeHour(List<Vehicle> vehicles) {
        int peakVolumeHour = 0;
        int peakVolume = 0;
        for (int hour = 0; hour < 24; hour++) {
            List<Vehicle> vehiclesOnCurrentHour = helper.getVehiclesByTimePeriod(vehicles, hour);
            if (vehiclesOnCurrentHour.size() > peakVolume) {
                peakVolumeHour = hour;
                peakVolume = vehiclesOnCurrentHour.size();
            }
        }
        return new PeakVolumeTime(peakVolumeHour, 0);
    }


    public void getReportPerHalfAnHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HALF_AN_HOUR);
        PeakVolumeTime peakVolumeTime = getPeakVolumeHalfAnHour(vehicles, 2);
        System.out.println("  \t\tFrom " + helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes())) + " to " + helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes() + 30)));
        formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeTime.getHour(), peakVolumeTime.getMinutes()));
    }

    public void getReportPer20Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_20_MINUTES);
        PeakVolumeTime peakVolumeTime = getPeakVolumeHalfAnHour(vehicles, 3);
        System.out.println("  \t\tFrom " + helper.getFormattedTime(peakVolumeTime.getHour(), peakVolumeTime.getMinutes()) + " to " + helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes() + 20)));
        formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeTime.getHour(), peakVolumeTime.getMinutes()));
    }

    public void getReportPer15Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_15_MINUTES);
        PeakVolumeTime peakVolumeTime = getPeakVolumeHalfAnHour(vehicles, 4);
        System.out.println("  \t\tFrom " + helper.getFormattedTime(peakVolumeTime.getHour(), peakVolumeTime.getMinutes()) + " to " + helper.getFormattedTime(peakVolumeTime.getHour(), (peakVolumeTime.getMinutes() + 15)));
        formatReport(helper.getVehiclesByTimePeriod(vehicles, peakVolumeTime.getHour(), peakVolumeTime.getMinutes()));
    }

    private PeakVolumeTime getPeakVolumeHalfAnHour(List<Vehicle> vehicles, int parts) {
        int peakVolumeHour = 0;
        int peakVolumeHalfAnHour = 0;
        int peakVolume = 0;
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < parts; half_part++) {
                List<Vehicle> vehiclesOnCurrentHour = helper.getVehiclesByTimePeriod(vehicles, hour, half_part * (60 / parts));
                if (vehiclesOnCurrentHour.size() > peakVolume) {
                    peakVolumeHour = hour;
                    peakVolumeHalfAnHour = half_part * (60 / parts);
                    peakVolume = vehiclesOnCurrentHour.size();
                }
            }
        }
        return new PeakVolumeTime(peakVolumeHour, peakVolumeHalfAnHour);
    }

    public void formatReport(List<Vehicle> vehicles) {
        output.print("\t\t\tTotal vehicles in Peak volume time: " + vehicles.size());
        output.print("\t\t\ttotal vehicles in peak volume time moving in North direction: " + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        output.print("\t\t\ttotal vehicles in peak volume time moving in South direction: " + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
