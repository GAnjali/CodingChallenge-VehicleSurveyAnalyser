package counter;

import exceptions.InvalidDataException;
import exceptions.InvalidTimeException;
import model.Direction;
import model.Vehicle;
import helper.Helper;

import java.util.ArrayList;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public class DataParser {
    private List<Vehicle> vehicles;
    private Helper helper;

    public DataParser() {
        vehicles = new ArrayList<>();
        helper = new Helper();
    }

    public int getVehiclesCount(List<String> records) throws InvalidDataException, InvalidTimeException {
        return getVehicles(records).size();
    }

    public List<Vehicle> getVehicles(List<String> records) throws InvalidDataException, InvalidTimeException {
        for (int recordIndex = 0; recordIndex < records.size() - 1; recordIndex++) {
            Direction directionOfVehicle = getDirectionOfVehicle(records.get(recordIndex), records.get(recordIndex + 1));
            if (directionOfVehicle.equals(Direction.NORTH)) {
                handleVehicleMovingInNorthDirection(records, recordIndex, vehicles);
            } else {
                handleVehicleMovingInSouthDirection(records, recordIndex, vehicles);
            }
            recordIndex = getNextRecordIndex(recordIndex, directionOfVehicle);
        }
        return vehicles;
    }

    private int getNextRecordIndex(int currentRecordIndex, Direction directionOfVehicle) {
        return directionOfVehicle.equals(Direction.NORTH) ? currentRecordIndex + 1 : currentRecordIndex + 3;
    }

    private Direction getDirectionOfVehicle(String record1, String record2) {
        return record1.startsWith(SENSOR1_NAME) & record2.startsWith(SENSOR1_NAME) ? Direction.NORTH : Direction.SOUTH;
    }

    private void handleVehicleMovingInNorthDirection(List<String> records, int recordIndex, List<Vehicle> vehicles) throws InvalidTimeException {
        int frontAxleTime = helper.getTime(records.get(recordIndex));
        int rearAxleTime = helper.getTime(records.get(recordIndex) + 1);
        vehicles.add(new Vehicle(Direction.NORTH, frontAxleTime, Double.parseDouble(helper.getSpeed(rearAxleTime - frontAxleTime))));
    }

    private void handleVehicleMovingInSouthDirection(List<String> records, int recordIndex, List<Vehicle> vehicles) throws InvalidDataException, InvalidTimeException {
        if (!isValidRecords(records, recordIndex)) {
            throw new InvalidDataException();
        }
        int frontAxleTime = helper.getTime(records.get(recordIndex));
        int rearAxleTime = helper.getTime(records.get(recordIndex) + 1);
        vehicles.add(new Vehicle(Direction.SOUTH, frontAxleTime, Double.parseDouble(helper.getSpeed(rearAxleTime - frontAxleTime))));
    }

    private boolean isValidRecords(List<String> records, int recordIndex) {
        String frontAxleSensor1Record = records.get(recordIndex);
        String frontAxleSensor2Record = records.get(recordIndex + 1);
        String rearAxleSensor1Record = records.get(recordIndex + 2);
        String rearAxleSensor2Record = records.get(recordIndex + 3);
        return frontAxleSensor1Record.startsWith(SENSOR1_NAME) && frontAxleSensor2Record.startsWith(SENSOR2_NAME) && rearAxleSensor1Record.startsWith(SENSOR1_NAME) && rearAxleSensor2Record.startsWith(SENSOR2_NAME);
    }
}
