package reportGenerator;

import model.Direction;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

import java.util.List;

public class VehicleCountReportGenerator implements DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) {
        output.print("***************Vehicle Count Report***************");
        getReport(vehicles);
    }

    public void formatReport(List<Vehicle> vehicles) {
        output.print("\t\t\tTotal vehicle count: " + vehicles.size());
        output.print("\t\t\ttotal vehicle count moving in North direction: " + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        output.print("\t\t\ttotal vehicle count moving in South direction: " + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
