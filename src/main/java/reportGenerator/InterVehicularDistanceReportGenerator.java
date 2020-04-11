package reportGenerator;

import model.Direction;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class InterVehicularDistanceReportGenerator implements DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) {
        output.print("***************Inter Vehicular Distance Report***************");
        getReport(vehicles);
    }

    public void formatReport(List<Vehicle> vehicles) {
        List<Vehicle> northBoundVehicles = helper.getVehiclesByDirection(vehicles, Direction.NORTH);
        List<Double> interVehicularDistance = calculateDistance(northBoundVehicles);
        output.print("\t\t\t\t North bound vehicles     = " + String.format("%.2f", helper.getAverage(interVehicularDistance)));

        List<Vehicle> southBoundVehicles = helper.getVehiclesByDirection(vehicles, Direction.SOUTH);
        interVehicularDistance = calculateDistance(southBoundVehicles);
        output.print("\t\t\t\t South bound vehicles     = " + String.format("%.2f", helper.getAverage(interVehicularDistance)));
    }

    public List<Double> calculateDistance(List<Vehicle> vehicles) {
        List<Double> interVehicularDistances = new ArrayList<>();
        final Vehicle previousVehicle = new Vehicle(null, 0, 0.00);
        vehicles.forEach(currentVehicle -> {
            double timeGapAmongVehiclesInSeconds = helper.getTimeGapAmongVehiclesInSeconds(previousVehicle.getPassingTimeInMilliSeconds(), currentVehicle.getPassingTimeInMilliSeconds());
            double speedGapAmongVehicles = Math.abs(previousVehicle.getSpeed() - currentVehicle.getSpeed());
            interVehicularDistances.add(Double.parseDouble(String.format("%.2f", timeGapAmongVehiclesInSeconds * speedGapAmongVehicles / 1000)));
            previousVehicle.setDirection(currentVehicle.getDirection());
            previousVehicle.setPassingTimeInMilliSeconds(currentVehicle.getPassingTimeInMilliSeconds());
            previousVehicle.setSpeed(currentVehicle.getSpeed());
        });
        return interVehicularDistances;
    }
}
