package reportGenerator.daywise;

import exceptions.UnableToCreateFileException;
import model.Direction;
import model.Vehicle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public class InterVehicularDistanceReportGenerator extends DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) throws UnableToCreateFileException, FileNotFoundException {
        writeToFile(INTER_VEHICULAR_DISTANCE_REPORT_FILE_NAME);
        printStream.print(INTER_VEHICULAR_DISTANCE_REPORT_GENERATOR_HEADING);
        getReport(vehicles);
    }

    public void formatReport(List<Vehicle> vehicles) {
        report(vehicles, Direction.NORTH);
        report(vehicles, Direction.SOUTH);
    }

    private void report(List<Vehicle> vehicles, Direction direction) {
        List<Double> interVehicularDistance = calculateDistance(helper.getVehiclesByDirection(vehicles, direction));
        printStream.print(getBoundedVehiclesMessage(direction) + String.format("%.2f", helper.getAverage(interVehicularDistance)));
    }

    private String getBoundedVehiclesMessage(Direction direction) {
        return direction.equals(Direction.NORTH) ? NORTH_BOUND_VEHICLES_MESSAGE : SOUTH_BOUND_VEHICLES_MESSAGE;
    }

    public List<Double> calculateDistance(List<Vehicle> vehicles) {
        List<Double> interVehicularDistance = new ArrayList<>();
        Vehicle previousVehicle = new Vehicle(null, 0, 0.00, 0);
        for (Vehicle currentVehicle : vehicles) {
            interVehicularDistance.add(getInterVehicularDistance(previousVehicle, currentVehicle));
            previousVehicle = currentVehicle;
        }
        return interVehicularDistance;
    }

    private Double getInterVehicularDistance(Vehicle previousVehicle, Vehicle currentVehicle) {
        double timeGapAmongVehiclesInSeconds = helper.getTimeGapAmongVehiclesInSeconds(previousVehicle.getPassingTimeInMilliSeconds(), currentVehicle.getPassingTimeInMilliSeconds());
        double speedGapAmongVehicles = Math.abs(previousVehicle.getSpeed() - currentVehicle.getSpeed());
        return Double.parseDouble(String.format("%.2f", timeGapAmongVehiclesInSeconds * speedGapAmongVehicles / 1000));
    }
}
