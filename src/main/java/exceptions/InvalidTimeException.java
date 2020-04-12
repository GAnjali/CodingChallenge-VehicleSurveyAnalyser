package exceptions;

import static helper.VehicleSurveyAnalyserConstants.INVALID_TIME_MESSAGE;

public class InvalidTimeException extends Exception{
    private final String message;

    public InvalidTimeException() {
        super();
        this.message = INVALID_TIME_MESSAGE;
    }

    @Override
    public String toString() {
        return message;
    }
}
