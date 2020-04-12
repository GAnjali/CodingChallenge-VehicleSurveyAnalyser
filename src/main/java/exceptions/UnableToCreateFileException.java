package exceptions;

import static helper.VehicleSurveyAnalyserConstants.UNABLE_TO_CREATE_FILE_MESSAGE;

public class UnableToCreateFileException extends Exception {
    private final String message;

    public UnableToCreateFileException(Exception exception) {
        super();
        this.message = UNABLE_TO_CREATE_FILE_MESSAGE + "\n" + exception;
    }

    @Override
    public String toString() {
        return message;
    }
}
