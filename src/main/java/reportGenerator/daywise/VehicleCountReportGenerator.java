package reportGenerator.daywise;

import exceptions.UnableToCreateFileException;
import model.Day;
import model.Direction;
import model.Vehicle;

import java.io.FileNotFoundException;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public class VehicleCountReportGenerator extends DayWiseReportGenerator {

    @Override
    public void generate(List<Vehicle> vehicles) throws UnableToCreateFileException, FileNotFoundException {
        writeToFile(VEHICLE_COUNT_REPORT_FILE_NAME);
        printStream.print(VEHICLE_COUNT_REPORT_GENERATOR_HEADING_TEMPLATE);
        getReport(vehicles);
    }

    @Override
    public void getReportDayWise(List<Vehicle> vehicles, Day day) {
        printStream.print(String.format(VEHICLE_COUNT_REPORT_PER_DAY_MESSAGE_TEMPLATE, Day.valueOfLabel(day.label), vehicles.size()));
        generateFullDayReport(vehicles);
    }

    @Override
    public void formatReport(List<Vehicle> vehicles) {
        printStream.print(TOTAL_VEHICLE_COUNT_MESSAGE_TEMPLATE + vehicles.size());
        printStream.print(NORTH_VEHICLES_COUNT_MESSAGE_TEMPLATE + util.getVehiclesByDirection(vehicles, Direction.NORTH).size());
        printStream.print(SOUTH_VEHICLES_COUNT_MESSAGE_TEMPLATE + util.getVehiclesByDirection(vehicles, Direction.SOUTH).size());
    }
}
