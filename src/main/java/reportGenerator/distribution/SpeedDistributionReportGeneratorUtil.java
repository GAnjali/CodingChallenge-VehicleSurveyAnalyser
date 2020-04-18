package reportGenerator.distribution;

import model.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

import static helper.VehicleSurveyAnalyserConstants.DISTRIBUTION_GAP;

public class SpeedDistributionReportGeneratorUtil {
    public List<Vehicle> getVehiclesOnCurrentSpeed(List<Vehicle> vehicles, float speed) {
        return vehicles.stream().filter(vehicle -> vehicle.isSpeedInBetween(speed - DISTRIBUTION_GAP, speed)).collect(Collectors.toList());
    }
}
