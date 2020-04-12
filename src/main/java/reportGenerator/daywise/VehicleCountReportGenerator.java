package reportGenerator.daywise;

import exceptions.InvalidFileCreationException;
import model.Direction;
import model.Vehicle;

import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public class VehicleCountReportGenerator extends DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) throws InvalidFileCreationException {
        writeToFile(VEHICLE_COUNT_REPORT_FILE_NAME);
        printStream.print(VEHICLE_COUNT_REPORT_GENERATOR_HEADING);
        getReport(vehicles);
    }

    public void formatReport(List<Vehicle> vehicles) {
        printStream.print(TOTAL_VEHICLE_COUNT + vehicles.size());
        printStream.print(NORTH_VEHICLES_COUNT + helper.getVehicleCountByDirection(vehicles, Direction.NORTH));
        printStream.print(SOUTH_VEHICLES_COUNT + helper.getVehicleCountByDirection(vehicles, Direction.SOUTH));
    }
}
