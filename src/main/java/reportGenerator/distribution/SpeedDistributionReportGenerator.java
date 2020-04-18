package reportGenerator.distribution;

import exceptions.UnableToCreateFileException;
import model.Vehicle;

import java.io.FileNotFoundException;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public class SpeedDistributionReportGenerator extends DistributionReportGenerator {
    private SpeedDistributionReportGeneratorUtil util;

    public SpeedDistributionReportGenerator() {
        util = new SpeedDistributionReportGeneratorUtil();
    }

    @Override
    public void generate(List<Vehicle> vehicles) throws UnableToCreateFileException, FileNotFoundException {
        writeToFile(SPEED_DISTRIBUTION_REPORT_FILE_NAME);
        printStream.print(SPEED_DISTRIBUTION_REPORT_GENERATOR_HEADING_TEMPLATE);
        getReport(vehicles);
    }

    private void getReport(List<Vehicle> vehicles) {
        Double highestSpeedOfVehicle = getHighestSpeed(vehicles);
        for (float speed = DISTRIBUTION_GAP; speed <= highestSpeedOfVehicle + DISTRIBUTION_GAP; speed = speed + DISTRIBUTION_GAP) {
            List<Vehicle> vehiclesOnCurrentSpeed = util.getVehiclesOnCurrentSpeed(vehicles, speed);
            String percentageOfVehiclesOnCurrentSpeed = getPercentageOfVehiclesOnCurrentSpeed(vehiclesOnCurrentSpeed, vehicles);
            printStream.print(String.format(FROM_SPEED_TO_SPEED_TEMPLATE, formatSpeed(speed - DISTRIBUTION_GAP), formatSpeed(speed), percentageOfVehiclesOnCurrentSpeed));
        }
    }

    private String getPercentageOfVehiclesOnCurrentSpeed(List<Vehicle> vehiclesOnCurrentSpeed, List<Vehicle> vehicles) {
        float countOfVehiclesOnCurrentSpeed = vehiclesOnCurrentSpeed.size();
        float totalCountOfVehicles = vehicles.size();
        return String.format("%.2f", countOfVehiclesOnCurrentSpeed / totalCountOfVehicles * 100);
    }

    private String formatSpeed(float speed) {
        return String.format("%.2f", speed);
    }

    private Double getHighestSpeed(List<Vehicle> vehicles) {
        double speed = 0.0;
        Vehicle dummyVehicle = new Vehicle(null, 0, 0.0, 0);
        for (Vehicle vehicle : vehicles) {
            speed = dummyVehicle.getHighestSpeedAmong(vehicle);
            dummyVehicle = vehicle;
        }
        return speed;
    }
}
