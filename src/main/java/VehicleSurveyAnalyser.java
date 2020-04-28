import IO.DataLoader;
import exceptions.InvalidDataException;
import exceptions.InvalidTimeException;
import exceptions.NoSuchFileFoundException;
import exceptions.UnableToCreateFileException;
import model.Sensor;
import model.Vehicle;
import model.TollCounter;
import reportGenerator.ReportGenerator;
import reportGenerator.daywise.InterVehicularDistanceReportGenerator;
import reportGenerator.daywise.PeakVolumeTimesReportGenerator;
import reportGenerator.daywise.VehicleCountReportGenerator;
import reportGenerator.distribution.SpeedDistributionReportGenerator;

import java.io.IOException;
import java.util.List;

import static helper.VehicleSurveyAnalyserConstants.*;

public class VehicleSurveyAnalyser {
    private static List<String> records;

    public static void main(String[] args) throws IOException, NoSuchFileFoundException, InvalidDataException, InvalidTimeException, UnableToCreateFileException {
        initialize();
        generateReports();
    }

    private static void generateReports() throws IOException, UnableToCreateFileException, InvalidDataException, InvalidTimeException {
        Sensor sensorA = new Sensor(SENSOR_A_LABEL);
        Sensor sensorB = new Sensor(SENSOR_B_LABEL);
        TollCounter tollCounter = new TollCounter(sensorA, sensorB, records);
        List<Vehicle> vehicles = tollCounter.getVehicles();
        ReportGenerator[] reportGenerator = {new VehicleCountReportGenerator(), new PeakVolumeTimesReportGenerator(), new SpeedDistributionReportGenerator(), new InterVehicularDistanceReportGenerator()};
        for (ReportGenerator generator : reportGenerator) {
            generator.generate(vehicles);
        }
    }

    private static void initialize() throws IOException, NoSuchFileFoundException {
        DataLoader dataLoader = new DataLoader(DATA_FILE);
        records = dataLoader.loadData();
    }
}
