package reportGenerator;

import IO.output.Output;
import helper.Helper;
import model.Direction;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeakVolumeTimesReportGenerator {
    private Helper helper;
    private Output output;

    public PeakVolumeTimesReportGenerator() {
        helper = new Helper();
        output = new Output();
    }

    public void generateReport(List<Vehicle> vehicles) {
        output.print("***************Peak Volume Times report***************");
        for (long day = 0; day < helper.getTotalDays(vehicles); day++) {
            List<Vehicle> vehiclesOnDay = helper.getVehiclesByDay(day, vehicles);
            generateFullDayReport(day, vehiclesOnDay);
        }
    }

    public void generateFullDayReport(long day, List<Vehicle> vehicles) {
        output.print("\n\tFull day report on Day " + (day + 1));
        List<String> times = Arrays.asList("MORNING/EVENING", "PER_HOUR", "PER_HALF_AN_HOUR", "PER_20_MINUTES", "PER_15_MINUTES");
        for (String time : times) {
            if (time.equals("MORNING/EVENING")) {
                output.print("\n\t\tMorning Vs Evening:");
                int countOfVehiclesInMorning = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.MORNING).size();
                int countOfVehiclesInEvening = helper.getVehiclesByTimePeriod(vehicles, TimePeriod.EVENING).size();
                if (countOfVehiclesInMorning > countOfVehiclesInEvening) {
                    getReport(TimePeriod.MORNING, vehicles);
                } else {
                    getReport(TimePeriod.EVENING, vehicles);
                }
            } else if (time.equals("PER_HOUR")) {
                output.print("\n\t\tPer Hour:");
                getReport(TimePeriod.PER_HOUR, vehicles);
            } else if (time.equals("PER_HALF_AN_HOUR")) {
                output.print("\n\t\tPer Half an Hour:");
                getReport(TimePeriod.PER_HALF_AN_HOUR, vehicles);
            } else if (time.equals("PER_20_MINUTES")) {
                output.print("\n\t\tPer 20 Minutes:");
                getReport(TimePeriod.PER_20_MINUTES, vehicles);
            } else if (time.equals("PER_15_MINUTES")) {
                output.print("\n\t\tPer 15 Minutes:");
                getReport(TimePeriod.PER_15_MINUTES, vehicles);
            }
        }
    }

    private void getReport(TimePeriod timePeriod, List<Vehicle> vehicles) {
        if (timePeriod.equals(TimePeriod.MORNING)) {
            System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(0) + " to " + helper.getFormattedTime(12));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, timePeriod));
        } else if (timePeriod.equals(TimePeriod.EVENING)) {
            System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(18) + " to " + helper.getFormattedTime(24));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, timePeriod));
        } else if (timePeriod.equals(TimePeriod.PER_HOUR)) {
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
        } else if (timePeriod.equals(TimePeriod.PER_HALF_AN_HOUR)) {
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
        } else if (timePeriod.equals(TimePeriod.PER_20_MINUTES)) {
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
        } else if (timePeriod.equals(TimePeriod.PER_15_MINUTES)) {
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
    }

    private void formatReport(List<Vehicle> vehicles) {
        output.print("\t\t\tTotal vehicles in Peak volume time: " + vehicles.size());
        output.print("\t\t\ttotal vehicles in peak volume time moving in North direction: " + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        output.print("\t\t\ttotal vehicles in peak volume time moving in South direction: " + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
