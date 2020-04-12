package reportGenerator.distribution;

import exceptions.UnableToCreateFileException;
import helper.Helper;
import model.Vehicle;
import reportGenerator.ReportGenerator;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.SPEED_DISTRIBUTION_REPORT_FILE_NAME;
import static helper.VehicleSurveyAnalyserConstants.SPEED_DISTRIBUTION_REPORT_GENERATOR_HEADING;

public class SpeedDistributionReportGenerator implements ReportGenerator {
    Helper helper = new Helper();
    PrintStream printStream = null;

    public void writeToFile(String file) throws UnableToCreateFileException, FileNotFoundException {
        printStream = getOutputStream(file);
    }

    @Override
    public void generate(List<Vehicle> vehicles) throws UnableToCreateFileException, FileNotFoundException {
        writeToFile(SPEED_DISTRIBUTION_REPORT_FILE_NAME);
        printStream.print("\n" + SPEED_DISTRIBUTION_REPORT_GENERATOR_HEADING);
        Double highestSpeedOfVehicle = getHighestSpeed(vehicles);
        for (float speed = 5; speed <= highestSpeedOfVehicle; speed = speed + 5) {
            List<Vehicle> vehiclesOnCurrentSpeed = helper.getVehiclesOnCurrentSpeed(vehicles, speed);
            float countOnSpeed = vehiclesOnCurrentSpeed.size();
            float totalCount = vehicles.size();
            String percentage = String.format("%.2f", countOnSpeed / totalCount * 100);
            printStream.print("\n\tSpeed from " + formatSpeed(speed - 5) + " to " + formatSpeed(speed) + " = " + percentage + "%");
        }
    }

    private String formatSpeed(float speed) {
        return String.format("%.2f", speed);
    }

    private Double getHighestSpeed(List<Vehicle> vehicles) {
        return vehicles.stream().max(Comparator.comparingDouble(Vehicle::getSpeed)).get().getSpeed();
    }
}
