package exceptions;

import static constants.VehicleSurveyAnalyserConstants.INVALID_FILE_CREATION_MESSAGE;

public class InvalidFileCreationException extends Exception {
    private final String message;

    public InvalidFileCreationException() {
        super();
        this.message = INVALID_FILE_CREATION_MESSAGE;
    }

    @Override
    public String toString() {
        return message;
    }
}