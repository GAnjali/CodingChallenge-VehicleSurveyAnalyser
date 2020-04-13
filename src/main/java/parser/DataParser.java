package parser;

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

    public List<Vehicle> parse(List<String> records) throws InvalidDataException, InvalidTimeException {
        int day = 0;
        String oldRecord = null;
        for (int recordIndex = 0; recordIndex < records.size() - 1; recordIndex++) {
            String currentRecord = records.get(recordIndex);
            Direction directionOfVehicle = getDirectionOfVehicle(records.get(recordIndex), records.get(recordIndex + 1));
            addVehicle(records, recordIndex, vehicles, day, directionOfVehicle);
            if (isNextDay(oldRecord, currentRecord))
                day++;
            oldRecord = currentRecord;
            recordIndex = getNextRecordIndex(recordIndex, directionOfVehicle);
        }
        return vehicles;
    }

    private Direction getDirectionOfVehicle(String record1, String record2) {
        return record1.startsWith(SENSOR1_NAME) & record2.startsWith(SENSOR1_NAME) ? Direction.NORTH : Direction.SOUTH;
    }

    private void addVehicle(List<String> records, int recordIndex, List<Vehicle> vehicles, int day, Direction direction) throws InvalidDataException, InvalidTimeException {
        if (!isValidRecords(records, recordIndex, direction)) {
            throw new InvalidDataException();
        }
        int frontAxleTime = helper.getTime(records.get(recordIndex));
        int rearAxleTime = helper.getTime(records.get(recordIndex) + 1);
        vehicles.add(new Vehicle(direction, frontAxleTime, helper.getSpeed(rearAxleTime - frontAxleTime), day));
    }

    private boolean isValidRecords(List<String> records, int recordIndex, Direction direction) {
        if (direction.equals(Direction.SOUTH)) {
            String frontAxleSensor1Record = records.get(recordIndex);
            String frontAxleSensor2Record = records.get(recordIndex + 1);
            String rearAxleSensor1Record = records.get(recordIndex + 2);
            String rearAxleSensor2Record = records.get(recordIndex + 3);
            return frontAxleSensor1Record.startsWith(SENSOR1_NAME) && frontAxleSensor2Record.startsWith(SENSOR2_NAME) && rearAxleSensor1Record.startsWith(SENSOR1_NAME) && rearAxleSensor2Record.startsWith(SENSOR2_NAME);
        }
        String frontAxleSensor1Record = records.get(recordIndex);
        String rearAxleSensor1Record = records.get(recordIndex + 1);
        return frontAxleSensor1Record.startsWith(SENSOR1_NAME) && rearAxleSensor1Record.startsWith(SENSOR1_NAME);
    }

    private boolean isNextDay(String previous, String current) throws InvalidTimeException {
        return helper.getTime(current) < helper.getTime(previous);
    }

    private int getNextRecordIndex(int currentRecordIndex, Direction directionOfVehicle) {
        return directionOfVehicle.equals(Direction.NORTH) ? currentRecordIndex + 1 : currentRecordIndex + 3;
    }
}
