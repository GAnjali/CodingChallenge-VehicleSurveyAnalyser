package counter;

import exceptions.InvalidDataException;
import model.Direction;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static constants.VehicleSurveyAnalyserConstants.SENSOR1_NAME;
import static constants.VehicleSurveyAnalyserConstants.SENSOR2_NAME;

public class VehicleCounter {
    public int getVehiclesCount(List<String> records) throws InvalidDataException {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int recordIndex = 0; recordIndex < records.size() - 1; recordIndex++) {
            if (isVehicleMovingInNorthDirection(records.get(recordIndex), records.get(recordIndex+1))) {
                addVehicleMovingInNorthDirection(records, recordIndex, vehicles);
                recordIndex++;
            } else {
                addVehicleMovingInSouthDirection(records, recordIndex, vehicles);
                recordIndex = recordIndex + 3;
            }
        }
        return vehicles.size();
    }

    private void addVehicleMovingInSouthDirection(List<String> records, int recordIndex, List<Vehicle> vehicles) throws InvalidDataException {
        String frontAxleSensor1Record = records.get(recordIndex);
        String frontAxleSensor2Record = records.get(recordIndex + 1);
        String rearAxleSensor1Record = records.get(recordIndex + 2);
        String rearAxleSensor2Record = records.get(recordIndex + 3);
        if (!isValidRecords(frontAxleSensor1Record, frontAxleSensor2Record, rearAxleSensor1Record, rearAxleSensor2Record)) {
            throw new InvalidDataException();
        }
        int frontAxleTime = Integer.parseInt(frontAxleSensor1Record.substring(1));
        int rearAxleTime = Integer.parseInt(rearAxleSensor1Record.substring(1));
        vehicles.add(new Vehicle(frontAxleTime, rearAxleTime, Direction.SOUTH, 1));
    }

    private boolean isValidRecords(String frontAxleSensor1Record, String frontAxleSensor2Record, String rearAxleSensor1Record, String rearAxleSensor2Record) {
        return frontAxleSensor1Record.startsWith(SENSOR1_NAME) && frontAxleSensor2Record.startsWith(SENSOR2_NAME) && rearAxleSensor1Record.startsWith(SENSOR1_NAME) && rearAxleSensor2Record.startsWith(SENSOR2_NAME);
    }

    private void addVehicleMovingInNorthDirection(List<String> records, int recordIndex, List<Vehicle> vehicles) {
        int frontAxleTime = Integer.parseInt(records.get(recordIndex).substring(1));
        int rearAxleTime = Integer.parseInt(records.get(recordIndex + 1).substring(1));
        vehicles.add(new Vehicle(frontAxleTime, rearAxleTime, Direction.NORTH, 1));
    }

    private boolean isVehicleMovingInNorthDirection(String record1, String record2) {
        return record1.startsWith(SENSOR1_NAME) & record2.startsWith(SENSOR1_NAME);
    }
}
