package model;

import exceptions.InvalidDataException;
import exceptions.InvalidTimeException;
import helper.DataParserUtil;

import java.util.ArrayList;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public class DataParser {
    private List<Vehicle> vehicles;
    private DataParserUtil dataParserUtil;

    public DataParser() {
        vehicles = new ArrayList<>();
        dataParserUtil = new DataParserUtil();
    }

    public List<Vehicle> parse(List<String> records) throws InvalidDataException, InvalidTimeException {
        int day = 0;
        String previousRecord = null;
        for (int recordIndex = 0; recordIndex < records.size() - 1; recordIndex++) {
            String currentRecord = records.get(recordIndex);
            Direction directionOfVehicle = calculateDirection(records.get(recordIndex), records.get(recordIndex + 1));
            day = calculateDay(previousRecord, currentRecord, day);
            addVehicle(records, recordIndex, vehicles, day, directionOfVehicle);
            previousRecord = currentRecord;
            recordIndex = getNextRecordIndex(recordIndex, directionOfVehicle);
        }
        return vehicles;
    }

    private Direction calculateDirection(String record1, String record2) {
        return record1.startsWith(SENSOR1_NAME) & record2.startsWith(SENSOR1_NAME) ? Direction.NORTH : Direction.SOUTH;
    }

    private int calculateDay(String previousRecord, String currentRecord, int day) throws InvalidTimeException {
        if (isNextDay(previousRecord, currentRecord)) day++;
        return day;
    }

    private boolean isNextDay(String previousRecord, String currentRecord) throws InvalidTimeException {
        return dataParserUtil.getExtractedTime(currentRecord) < dataParserUtil.getExtractedTime(previousRecord);
    }

    private void addVehicle(List<String> records, int recordIndex, List<Vehicle> vehicles, int day, Direction direction) throws InvalidDataException, InvalidTimeException {
        if (!isValidRecords(records, recordIndex, direction)) {
            throw new InvalidDataException(records.get(recordIndex));
        }
        int frontAxleTime = dataParserUtil.getExtractedTime(records.get(recordIndex));
        double speedOfVehicle = calculateSpeed(records, recordIndex, direction);
        vehicles.add(new Vehicle(direction, frontAxleTime, speedOfVehicle, day));
    }

    private Double calculateSpeed(List<String> records, int recordIndex, Direction direction) throws InvalidTimeException {
        int frontAxleTime = dataParserUtil.getExtractedTime(records.get(recordIndex));
        int rearAxleTime;
        if (direction.equals(Direction.NORTH))
            rearAxleTime = dataParserUtil.getExtractedTime(records.get(recordIndex + 1));
        else rearAxleTime = dataParserUtil.getExtractedTime(records.get(recordIndex + 2));
        return dataParserUtil.calculateSpeed(rearAxleTime - frontAxleTime);
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

    private int getNextRecordIndex(int currentRecordIndex, Direction directionOfVehicle) {
        return directionOfVehicle.equals(Direction.NORTH) ? currentRecordIndex + 1 : currentRecordIndex + 3;
    }
}
