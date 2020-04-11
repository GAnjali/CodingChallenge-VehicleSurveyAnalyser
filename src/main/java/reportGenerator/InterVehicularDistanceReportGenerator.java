package reportGenerator;

import model.Direction;
import model.Vehicle;
import reportGenerator.timeperiod.TimePeriod;

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

    @Override
    public void getReportForMorningOrEvening(List<Vehicle> vehicles, TimePeriod timePeriod) {
        output.print("\t\t" + timePeriod);
        if (timePeriod.equals(TimePeriod.MORNING))
            output.print("\t\t\tRough distance between vehicles during Morning (" + helper.getFormattedTime(0) + " to " + helper.getFormattedTime(12) + ")");
        else
            output.print("\t\t\tRough distance between vehicles during Morning (" + helper.getFormattedTime(18) + " to " + helper.getFormattedTime(24) + ")");
        formatReport(vehicles);
    }

    @Override
    public void getReportPerHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HOUR);
        for (int hour = 0; hour < 24; hour++) {
            System.out.println("  \t\tFrom HOUR " + helper.getFormattedTime(hour) + " to " + helper.getFormattedTime(hour + 1));
            formatReport(helper.getVehiclesByTimePeriod(vehicles, hour));
        }
    }

    @Override
    public void getReportPerHalfAnHour(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_HALF_AN_HOUR);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 2; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 30)) + " to " + helper.getFormattedTime(hour, ((half_part * 30) + 30)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 30));
            }
        }
    }

    private void formatReport(List<Vehicle> vehicles) {
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

    @Override
    public void getReportPer15Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_15_MINUTES);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 4; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 15)) + " to " + helper.getFormattedTime(hour, ((half_part * 15) + 15)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 15));
            }
        }
    }

    @Override
    public void getReportPer20Minutes(List<Vehicle> vehicles) {
        output.print("\t\t" + TimePeriod.PER_20_MINUTES);
        for (int hour = 0; hour < 24; hour++) {
            for (int half_part = 0; half_part < 3; half_part++) {
                System.out.println("  \t\tFrom " + helper.getFormattedTime(hour, (half_part * 20)) + " to " + helper.getFormattedTime(hour, ((half_part * 20) + 20)));
                formatReport(helper.getVehiclesByTimePeriod(vehicles, hour, half_part * 20));
            }
        }
    }
}
