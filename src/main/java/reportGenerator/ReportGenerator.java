package reportGenerator;

import model.Vehicle;

import java.util.List;

public interface ReportGenerator {
    void generate(List<Vehicle> vehicles);
}
