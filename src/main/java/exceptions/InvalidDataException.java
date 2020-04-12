package exceptions;

import static helper.VehicleSurveyAnalyserConstants.INVALID_DATA_MESSAGE;

public class InvalidDataException extends Exception {
    private final String message;

    public InvalidDataException() {
        super();
        this.message = INVALID_DATA_MESSAGE;
    }

    @Override
    public String toString() {
        return message;
    }
}
