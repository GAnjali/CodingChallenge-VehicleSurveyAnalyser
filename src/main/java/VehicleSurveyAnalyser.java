import IO.Input;
import parser.DataParser;
import exceptions.InvalidDataException;
import exceptions.InvalidTimeException;
import exceptions.NoSuchFileFoundException;
import exceptions.UnableToCreateFileException;
import model.Vehicle;
import reportGenerator.ReportGenerator;
import reportGenerator.daywise.InterVehicularDistanceReportGenerator;
import reportGenerator.daywise.PeakVolumeTimesReportGenerator;
import reportGenerator.daywise.VehicleCountReportGenerator;
import reportGenerator.distribution.SpeedDistributionReportGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class VehicleSurveyAnalyser {
    static List<String> records;

    public static void main(String[] args) throws IOException, NoSuchFileFoundException, InvalidDataException, InvalidTimeException, UnableToCreateFileException {
        initialize();
        generateReports();
    }

    private static void generateReports() throws InvalidDataException, InvalidTimeException, FileNotFoundException, UnableToCreateFileException {
        DataParser dataParser = new DataParser();
        List<Vehicle> vehicles = dataParser.parse(records);
        ReportGenerator[] reportGenerator = {new VehicleCountReportGenerator(), new PeakVolumeTimesReportGenerator(), new SpeedDistributionReportGenerator(), new InterVehicularDistanceReportGenerator()};
        for (ReportGenerator generator : reportGenerator) {
            generator.generate(vehicles);
        }
    }

    private static void initialize() throws IOException, NoSuchFileFoundException {
        Input input = new Input();
        records = input.loadData();
    }
}
