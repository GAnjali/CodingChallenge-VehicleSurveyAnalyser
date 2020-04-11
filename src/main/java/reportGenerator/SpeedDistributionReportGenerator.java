package reportGenerator;

import IO.output.Output;
import helper.Helper;
import model.Vehicle;

import java.util.Comparator;
import java.util.List;

import static constants.VehicleSurveyAnalyserConstants.SPEED_DISTRIBUTION_REPORT_GENERATOR_HEADING;

public class SpeedDistributionReportGenerator implements DistributionReportGenerator {
    Output output = new Output();
    Helper helper = new Helper();

    @Override
    public void generate(List<Vehicle> vehicles) {
        output.print(SPEED_DISTRIBUTION_REPORT_GENERATOR_HEADING);
        Double highestSpeedOfVehicle = getHighestSpeed(vehicles);
        for (float speed = 5; speed <= highestSpeedOfVehicle; speed = speed + 5) {
            List<Vehicle> vehiclesOnCurrentSpeed = helper.getVehiclesOnCurrentSpeed(vehicles, speed);
            float countOnSpeed = vehiclesOnCurrentSpeed.size();
            float totalCount = vehicles.size();
            String percentage = String.format("%.2f", countOnSpeed / totalCount * 100);
            output.print("\tSpeed from " + formatSpeed(speed - 5) + " to " + formatSpeed(speed) + " = " + percentage + "%");
        }
    }

    private String formatSpeed(float speed) {
        return String.format("%.2f", speed);
    }

    private Double getHighestSpeed(List<Vehicle> vehicles) {
        return vehicles.stream().max(Comparator.comparingDouble(Vehicle::getSpeed)).get().getSpeed();
    }
}
