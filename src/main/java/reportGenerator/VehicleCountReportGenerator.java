package reportGenerator;

import model.Direction;
import model.Vehicle;

import java.util.List;

import static constants.VehicleSurveyAnalyserConstants.*;

public class VehicleCountReportGenerator implements DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) {
        output.print(VEHICLE_COUNT_REPORT_GENERATOR_HEADING);
        getReport(vehicles);
    }

    public void formatReport(List<Vehicle> vehicles) {
        output.print(TOTAL_VEHICLE_COUNT + vehicles.size());
        output.print(NORTH_VEHICLES_COUNT + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        output.print(SOUTH_VEHICLES_COUNT + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
