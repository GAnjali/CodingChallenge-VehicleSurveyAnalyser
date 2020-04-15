package exceptions;

import static helper.VehicleSurveyAnalyserConstants.INVALID_TIME_MESSAGE;

public class InvalidTimeException extends Exception {
    private final String message;

    public InvalidTimeException(Exception exception) {
        super();
        this.message = INVALID_TIME_MESSAGE + "\n" + exception;
    }

    @Override
    public String toString() {
        return message;
    }
}
