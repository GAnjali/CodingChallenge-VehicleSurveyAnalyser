package exceptions;

import static helper.VehicleSurveyAnalyserConstants.INVALID_DATA_MESSAGE;

public class InvalidDataException extends Exception {
    private final String message;

    public InvalidDataException(String record) {
        super();
        this.message = INVALID_DATA_MESSAGE + record;
    }

    @Override
    public String toString() {
        return message;
    }
}
