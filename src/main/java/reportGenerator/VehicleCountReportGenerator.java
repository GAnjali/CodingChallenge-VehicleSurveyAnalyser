package reportGenerator;

import model.Direction;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

import java.util.List;

public class VehicleCountReportGenerator implements ReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) {
        output.print("***************Vehicle Count Report***************");
        getReport(vehicles);
    }

    public void getReportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        output.print("\t\t" + timePeriod);
        formatReport(helper.getVehiclesByTimePeriod(vehicles, timePeriod));
    }

    public void getReportPer15Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_15_MINUTES);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 4; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 15)) + " to " + helper.getFormattedTime(hour, ((half_part * 15) + 15)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 15));
            }
        }
    }

    public void getReportPer20Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_20_MINUTES);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 3; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 20)) + " to " + helper.getFormattedTime(hour, ((half_part * 20) + 20)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 20));
            }
        }
    }

    public void getReportPerHalfAnHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HALF_AN_HOUR);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 2; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 30)) + " to " + helper.getFormattedTime(hour, ((half_part * 30) + 30)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 30));
            }
        }
    }

    public void getReportPerHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HOUR);
        for (int hour = 0; hour < 24; hour++) {
            System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(hour) + " to " + helper.getFormattedTime(hour + 1));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, hour));
        }
    }

    private void formatReport(List<Vehicle> vehicles) {
        output.print("\t\t\tTotal vehicle count: " + vehicles.size());
        output.print("\t\t\ttotal vehicle count moving in North direction: " + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        output.print("\t\t\ttotal vehicle count moving in South direction: " + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
