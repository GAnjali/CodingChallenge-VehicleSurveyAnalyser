package reportGenerator;

import IO.output.Output;
import helper.Helper;
import model.Vehicle;

import java.util.List;

public interface ReportGenerator {
    Output output = new Output();
    Helper helper = new Helper();

    void generate(List<Vehicle> vehicles);

    default void getReport(List<Vehicle> vehicles) {
        for (long day = 0; day < helper.getTotalDays(vehicles); day++) {
            List<Vehicle> vehiclesOnDay = helper.getVehiclesByDay(day, vehicles);
            generateFullDayReport(day, vehiclesOnDay);
        }
    }

    void generateFullDayReport(long day, List<Vehicle> vehiclesOnDay);
}
