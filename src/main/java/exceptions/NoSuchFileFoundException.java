package exceptions;

import static helper.VehicleSurveyAnalyserConstants.NO_FILE_FOUND_MESSAGE;

public class NoSuchFileFoundException extends Exception {
    private final String message;

    public NoSuchFileFoundException() {
        super();
        this.message = NO_FILE_FOUND_MESSAGE;
    }

    @Override
    public String toString() {
        return message;
    }
}
