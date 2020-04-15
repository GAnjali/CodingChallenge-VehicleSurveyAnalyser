package reportGenerator.daywise;

import model.Direction;
import model.Vehicle;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class InterVehicularDistanceReportGeneratorUtil {
    public List<Vehicle> getVehiclesByDirection(List<Vehicle> vehicles, Direction direction) {
        return vehicles.stream().filter(vehicle -> vehicle.getDirection().equals(direction)).collect(Collectors.toList());
    }

    public double getTimeGapAmongVehiclesInSeconds(int firstVehiclePassingTime, int secondVehiclePassingTime) {
        int gapInMilliseconds = firstVehiclePassingTime > secondVehiclePassingTime ? firstVehiclePassingTime - secondVehiclePassingTime : secondVehiclePassingTime - firstVehiclePassingTime;
        return gapInMilliseconds / 1000;
    }

    public double getAverage(List<Double> interVehicularDistance) {
        OptionalDouble average = interVehicularDistance.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}
