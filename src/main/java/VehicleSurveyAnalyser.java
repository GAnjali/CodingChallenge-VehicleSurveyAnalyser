import IO.Input;
import exceptions.NoSuchFileFoundException;

import java.io.IOException;

public class VehicleSurveyAnalyser {
    public static void main(String[] args) throws IOException, NoSuchFileFoundException {
        initialize();
    }

    private static void initialize() throws IOException, NoSuchFileFoundException {
        Input input = new Input();
        input.loadFile();
    }
}
