package reportGenerator;

import model.Direction;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

import java.util.ArrayList;
import java.util.List;

public class PeakVolumeTimesReportGenerator implements DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) {
        output.print("***************Peak Volume Times Report***************");
        getReport(vehicles);
    }

    @Override
    public void getReportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        if(timePeriod.equals(TimePeriod.MORNING)){
            output.print("\n\t\tMorning Vs Evening:");
            int countOfVehiclesInMorning = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.MORNING).size();
            int countOfVehiclesInEvening = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.EVENING).size();
            if (countOfVehiclesInMorning > countOfVehiclesInEvening) {
                System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(0) + " to " + helper.getFormattedTime(12));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, TimePeriod.MORNING));
            } else {
                System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(18) + " to " + helper.getFormattedTime(24));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, TimePeriod.EVENING));
            }
        }
    }

    public void getReportPer15Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_15_MINUTES);
        int peakVolumeHour = 0;
        int peakVolumeMin = 0;
        int peakVolume = 0;
        List<Vehicle> vehiclesOnPeakVolumePer15Min = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 4; half_part++) {
                List<Vehicle> vehiclesOnCurrentHour = helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 15);
                if (vehiclesOnCurrentHour.size() > peakVolume) {
                    peakVolumeHour = hour;
                    peakVolumeMin = half_part * 15;
                    peakVolume = vehiclesOnCurrentHour.size();
                    vehiclesOnPeakVolumePer15Min = vehiclesOnCurrentHour;
                }
            }
        }
        System.out.println("  \t\tFrom " + helper.getFormattedTime(peakVolumeHour, peakVolumeMin) + " to " + helper.getFormattedTime(peakVolumeHour, (peakVolumeMin + 15)));
        formatReport(vehiclesOnPeakVolumePer15Min);
    }

    public void getReportPer20Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_20_MINUTES);
        int peakVolumeHour = 0;
        int peakVolumeMin = 0;
        int peakVolume = 0;
        List<Vehicle> vehiclesOnPeakVolumePer20Min = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 3; half_part++) {
                List<Vehicle> vehiclesOnCurrentHour = helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 20);
                if (vehiclesOnCurrentHour.size() > peakVolume) {
                    peakVolumeHour = hour;
                    peakVolumeMin = half_part * 20;
                    peakVolume = vehiclesOnCurrentHour.size();
                    vehiclesOnPeakVolumePer20Min = vehiclesOnCurrentHour;
                }
            }
        }
        System.out.println("  \t\tFrom " + helper.getFormattedTime(peakVolumeHour, peakVolumeMin) + " to " + helper.getFormattedTime(peakVolumeHour, (peakVolumeMin + 20)));
        formatReport(vehiclesOnPeakVolumePer20Min);
    }

    public void getReportPerHalfAnHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HALF_AN_HOUR);
        int peakVolumeHour = 0;
        int peakVolumeHalfAnHour = 0;
        int peakVolume = 0;
        List<Vehicle> vehiclesOnPeakVolumeHalfAnHour = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 2; half_part++) {
                List<Vehicle> vehiclesOnCurrentHour = helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 30);
                if (vehiclesOnCurrentHour.size() > peakVolume) {
                    peakVolumeHour = hour;
                    peakVolumeHalfAnHour = half_part * 30;
                    peakVolume = vehiclesOnCurrentHour.size();
                    vehiclesOnPeakVolumeHalfAnHour = vehiclesOnCurrentHour;
                }
            }
        }
        System.out.println("  \t\tFrom " + helper.getFormattedTime(peakVolumeHour, (peakVolumeHalfAnHour)) + " to " + helper.getFormattedTime(peakVolumeHour, (peakVolumeHalfAnHour + 30)));
        formatReport(vehiclesOnPeakVolumeHalfAnHour);
    }

    public void getReportPerHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HOUR);
        int peakVolumeHour = 0;
        int peakVolume = 0;
        List<Vehicle> vehiclesOnPeakVolumeHour = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            List<Vehicle> vehiclesOnCurrentHour = helper.getVehiclesByTimePeriod(vehicles, hour);
            if (vehiclesOnCurrentHour.size() > peakVolume) {
                peakVolumeHour = hour;
                peakVolume = vehiclesOnCurrentHour.size();
                vehiclesOnPeakVolumeHour = vehiclesOnCurrentHour;
            }
        }
        System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(peakVolumeHour) + " to " + helper.getFormattedTime(peakVolumeHour + 1));
        formatReport(vehiclesOnPeakVolumeHour);
    }

    private void formatReport(List<Vehicle> vehicles) {
        output.print("\t\t\tTotal vehicles in Peak volume time: " + vehicles.size());
        output.print("\t\t\ttotal vehicles in peak volume time moving in North direction: " + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        output.print("\t\t\ttotal vehicles in peak volume time moving in South direction: " + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
