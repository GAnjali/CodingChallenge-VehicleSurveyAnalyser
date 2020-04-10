package reportGenerator;

import IO.output.Output;
import helper.Helper;
import model.Vehicle;

import java.util.List;

public interface ReportGenerator {
    Output output = new Output();
    Helper helper = new Helper();

    default void generateReport(List<Vehicle> vehicles) {
        output.print("***************Peak Volume Times report***************");
        for (long day = 0; day < helper.getTotalDays(vehicles); day++) {
            List<Vehicle> vehiclesOnDay = helper.getVehiclesByDay(day, vehicles);
            generateFullDayReport(day, vehiclesOnDay);
        }
    }

    void generateFullDayReport(long day, List<Vehicle> vehiclesOnDay);
}
