package reportGenerator;

import IO.output.Output;
import helper.Helper;
import model.Direction;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

import java.util.List;

public class VehicleCountReportGenerator {
    private Helper helper;
    private Output output;

    public VehicleCountReportGenerator() {
        helper = new Helper();
        output = new Output();
    }

    public void generateReport(List<Vehicle> vehicles) {
        output.print("***************Day wise Vehicle count report***************");
        for (long day = 0; day < helper.getTotalDays(vehicles); day++) {
            List<Vehicle> vehiclesOnDay = helper.getVehiclesByDay(day, vehicles);
            generateFullDayReport(day, vehiclesOnDay);
        }
    }

    public void generateFullDayReport(long day, List<Vehicle> vehicles) {
        output.print("\n\tFull day report on Day " + (day + 1));
        for (TimePeriod timePeriod : TimePeriod.values()) {
            output.print("\t\t" + timePeriod);
            getReport(timePeriod, vehicles);
        }
    }

    private void getReport(TimePeriod timePeriod, List<Vehicle> vehicles) {
        if (timePeriod.equals(TimePeriod.MORNING)) {
            formatReport(helper.getVehiclesByTimePeriod(vehicles, timePeriod));
        } else if (timePeriod.equals(TimePeriod.EVENING)) {
            formatReport(helper.getVehiclesByTimePeriod(vehicles, timePeriod));
        } else if (timePeriod.equals(TimePeriod.PER_HOUR)) {
            for (int hour = 0; hour < 24; hour++) {
                System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(hour) + " to " + helper.getFormattedTime(hour + 1));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour));
            }
        } else if (timePeriod.equals(TimePeriod.PER_HALF_AN_HOUR)) {
            for (int hour = 0; hour < 24; hour++) {
                for (int half_part = 0; half_part < 2; half_part++) {
                    System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 30)) + " to " + helper.getFormattedTime(hour, ((half_part * 30) + 30)));
                    formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 30));
                }
            }
        } else if (timePeriod.equals(TimePeriod.PER_20_MINUTES)) {
            for (int hour = 0; hour < 24; hour++) {
                for (int half_part = 0; half_part < 3; half_part++) {
                    System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 20)) + " to " + helper.getFormattedTime(hour, ((half_part * 20) + 20)));
                    formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 20));
                }
            }
        }
    }

    private void formatReport(List<Vehicle> vehicles) {
        output.print("\t\t\tTotal vehicle count: " + vehicles.size());
        output.print("\t\t\ttotal vehicle count moving in North direction: " + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        output.print("\t\t\ttotal vehicle count moving in South direction: " + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
