package reportGenerator;

import model.Direction;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class InterVehicularDistanceReportGenerator implements DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) {
        output.print("***************Inter Vehicular Distance Report***************");
        getReport(vehicles);
    }

    public void formatReport(List<Vehicle> vehicles) {
        List<Vehicle> northBoundVehicles = getVehiclesByDirection(vehicles, Direction.NORTH);
        List<Double> interVehicularDistance = calculateDistance(northBoundVehicles);
        output.print("\t\t\t\t North bound vehicles     = " + String.format("%.2f", findAverage(interVehicularDistance)));

        List<Vehicle> southBoundVehicles = getVehiclesByDirection(vehicles, Direction.SOUTH);
        interVehicularDistance = calculateDistance(southBoundVehicles);
        output.print("\t\t\t\t South bound vehicles     = " + String.format("%.2f", findAverage(interVehicularDistance)));
    }

    private List<Vehicle> getVehiclesByDirection(List<Vehicle> vehicles, Direction direction) {
        return vehicles.stream().filter(vehicle -> vehicle.getDirection().equals(direction)).collect(Collectors.toList());
    }

    private double findAverage(List<Double> interVehicularDistance) {
        OptionalDouble average = interVehicularDistance.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public List<Double> calculateDistance(List<Vehicle> vehicles) {
        List<Double> interVehicularDistances = new ArrayList<>();
        final Vehicle previousVehicle = new Vehicle(null, 0, 0.00);
        vehicles.forEach(currentVehicle -> {
            double timeGapAmongVehiclesInSeconds = getTimeGapAmongVehiclesInSeconds(previousVehicle.getPassingTimeInMilliSeconds(), currentVehicle.getPassingTimeInMilliSeconds());
            double speedGapAmongVehicles = Math.abs(previousVehicle.getSpeed() - currentVehicle.getSpeed());
            interVehicularDistances.add(Double.parseDouble(String.format("%.2f", timeGapAmongVehiclesInSeconds * speedGapAmongVehicles / 1000)));
            previousVehicle.setDirection(currentVehicle.getDirection());
            previousVehicle.setPassingTimeInMilliSeconds(currentVehicle.getPassingTimeInMilliSeconds());
            previousVehicle.setSpeed(currentVehicle.getSpeed());
        });
        return interVehicularDistances;
    }

    private double getTimeGapAmongVehiclesInSeconds(int firstVehiclePassingTime, int secondVehiclePassingTime) {
        int gapInMilliseconds = firstVehiclePassingTime > secondVehiclePassingTime ? firstVehiclePassingTime - secondVehiclePassingTime : secondVehiclePassingTime - firstVehiclePassingTime;
        return gapInMilliseconds / 1000;
    }
}
