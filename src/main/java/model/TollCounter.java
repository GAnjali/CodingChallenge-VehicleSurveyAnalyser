package model;

import exceptions.InvalidDataException;
import exceptions.InvalidTimeException;

import java.util.ArrayList;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.AVERAGE_WHEEL_BASE;

public class TollCounter {
    private Sensor sensorA;
    private Sensor sensorB;
    private List<String> records;

    public TollCounter(Sensor sensorA, Sensor sensorB, List<String> records) {
        this.sensorA = sensorA;
        this.sensorB = sensorB;
        this.records = records;
    }

    public List<Vehicle> getVehicles() throws InvalidTimeException, InvalidDataException {
        List<Vehicle> vehicles = new ArrayList<>();
        Day day = Day.MONDAY;
        String previousRecord = null;
        for (int recordIndex = 0; recordIndex < records.size() - 1; recordIndex++) {
            String currentRecord = records.get(recordIndex);
            Direction directionOfVehicle = extractDirection(records.get(recordIndex), records.get(recordIndex + 1));
            day = calculateDay(previousRecord, currentRecord, day);
            addVehicle(records, recordIndex, vehicles, day, directionOfVehicle);
            previousRecord = currentRecord;
            recordIndex = getNextRecordIndex(recordIndex, directionOfVehicle);
        }
        return vehicles;
    }

    private Direction extractDirection(String previousRecord, String currentRecord) {
        return sensorA.isEqual(getSensorLabel(previousRecord)) & sensorA.isEqual(getSensorLabel(currentRecord)) ? Direction.NORTH : Direction.SOUTH;
    }

    private String getSensorLabel(String record) {
        return String.valueOf(record.charAt(0));
    }

    private Day calculateDay(String previousRecord, String currentRecord, Day day) throws InvalidTimeException {
        if (isNextDay(previousRecord, currentRecord)) {
            day = Day.valueOfLabel(day.label + 1);
        }
        return day;
    }

    private boolean isNextDay(String previousRecord, String currentRecord) throws InvalidTimeException {
        return getExtractedTime(currentRecord) < getExtractedTime(previousRecord);
    }

    private void addVehicle(List<String> records, int recordIndex, List<Vehicle> vehicles, Day day, Direction direction) throws InvalidDataException, InvalidTimeException {
        if (!isValidRecords(records, recordIndex, direction)) {
            throw new InvalidDataException(records.get(recordIndex));
        }
        int frontAxleTime = getExtractedTime(records.get(recordIndex));
        double speedOfVehicle = extractSpeed(records, recordIndex, direction);
        vehicles.add(new Vehicle(direction, frontAxleTime, speedOfVehicle, day));
    }

    private Double extractSpeed(List<String> records, int recordIndex, Direction direction) throws InvalidTimeException {
        int frontAxleTime = getExtractedTime(records.get(recordIndex));
        int rearAxleTime;
        if (direction.equals(Direction.NORTH)) rearAxleTime = getExtractedTime(records.get(recordIndex + 1));
        else rearAxleTime = getExtractedTime(records.get(recordIndex + 2));
        return calculateSpeed(rearAxleTime - frontAxleTime);
    }

    private boolean isValidRecords(List<String> records, int recordIndex, Direction direction) {
        if (direction.equals(Direction.SOUTH)) {
            String frontAxleSensor1Record = records.get(recordIndex);
            String frontAxleSensor2Record = records.get(recordIndex + 1);
            String rearAxleSensor1Record = records.get(recordIndex + 2);
            String rearAxleSensor2Record = records.get(recordIndex + 3);
            return sensorA.isEqual(getSensorLabel(frontAxleSensor1Record)) && sensorB.isEqual(getSensorLabel(frontAxleSensor2Record)) && sensorA.isEqual(getSensorLabel(rearAxleSensor1Record)) && sensorB.isEqual(getSensorLabel(rearAxleSensor2Record));
        }
        String frontAxleSensor1Record = records.get(recordIndex);
        String rearAxleSensor1Record = records.get(recordIndex + 1);
        return sensorA.isEqual(getSensorLabel(frontAxleSensor1Record)) && sensorA.isEqual(getSensorLabel(rearAxleSensor1Record));
    }

    private int getNextRecordIndex(int currentRecordIndex, Direction directionOfVehicle) {
        return directionOfVehicle.equals(Direction.NORTH) ? currentRecordIndex + 1 : currentRecordIndex + 3;
    }

    private Double calculateSpeed(int timeInMilliSeconds) {
        double speed = (Double.parseDouble(AVERAGE_WHEEL_BASE) / timeInMilliSeconds) * 1000;
        return Double.parseDouble(String.format("%.02f", speed));
    }

    private int getExtractedTime(String record) throws InvalidTimeException {
        try {
            if (record == null) return 0;
            return Integer.parseInt(record.substring(1));
        } catch (Exception exception) {
            throw new InvalidTimeException(exception);
        }
    }
}
