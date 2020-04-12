package reportGenerator.daywise;

import exceptions.InvalidFileCreationException;
import model.Direction;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static constants.VehicleSurveyAnalyserConstants.INTER_VEHICULAR_DISTANCE_REPORT_FILE_NAME;
import static constants.VehicleSurveyAnalyserConstants.INTER_VEHICULAR_DISTANCE_REPORT_GENERATOR_HEADING;

public class InterVehicularDistanceReportGenerator extends DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) throws InvalidFileCreationException {
        writeToFile(INTER_VEHICULAR_DISTANCE_REPORT_FILE_NAME);
        printStream.print(INTER_VEHICULAR_DISTANCE_REPORT_GENERATOR_HEADING);
        getReport(vehicles);
    }

    public void formatReport(List<Vehicle> vehicles) {
        List<Vehicle> northBoundVehicles = helper.getVehiclesByDirection(vehicles, Direction.NORTH);
        List<Double> interVehicularDistance = calculateDistance(northBoundVehicles);
        printStream.print("\n\t\t\t\t North bound vehicles     = " + String.format("%.2f", helper.getAverage(interVehicularDistance)));

        List<Vehicle> southBoundVehicles = helper.getVehiclesByDirection(vehicles, Direction.SOUTH);
        interVehicularDistance = calculateDistance(southBoundVehicles);
        printStream.print("\n\t\t\t\t South bound vehicles     = " + String.format("%.2f", helper.getAverage(interVehicularDistance)));
    }

    public List<Double> calculateDistance(List<Vehicle> vehicles) {
        List<Double> interVehicularDistances = new ArrayList<>();
        final Vehicle previousVehicle = new Vehicle(null, 0, 0.00);
        vehicles.forEach(currentVehicle -> {
            Double interVehicularDistance = getInterVehicularDistance(previousVehicle, currentVehicle);
            interVehicularDistances.add(interVehicularDistance);
            updatePreviousVehicle(previousVehicle, currentVehicle);
        });
        return interVehicularDistances;
    }

    private void updatePreviousVehicle(Vehicle previousVehicle, Vehicle currentVehicle) {
        previousVehicle.setDirection(currentVehicle.getDirection());
        previousVehicle.setPassingTimeInMilliSeconds(currentVehicle.getPassingTimeInMilliSeconds());
        previousVehicle.setSpeed(currentVehicle.getSpeed());
    }

    private Double getInterVehicularDistance(Vehicle previousVehicle, Vehicle currentVehicle) {
        double timeGapAmongVehiclesInSeconds = helper.getTimeGapAmongVehiclesInSeconds(previousVehicle.getPassingTimeInMilliSeconds(), currentVehicle.getPassingTimeInMilliSeconds());
        double speedGapAmongVehicles = Math.abs(previousVehicle.getSpeed() - currentVehicle.getSpeed());
        return Double.parseDouble(String.format("%.2f", timeGapAmongVehiclesInSeconds * speedGapAmongVehicles / 1000));
    }
}
