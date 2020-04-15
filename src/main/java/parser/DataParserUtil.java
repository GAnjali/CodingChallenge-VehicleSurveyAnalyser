package parser;

import exceptions.InvalidTimeException;

import static helper.VehicleSurveyAnalyserConstants.AVERAGE_WHEEL_BASE;

public class DataParserUtil {
    public Double calculateSpeed(int timeInMilliSeconds) {
        double speed = (Double.parseDouble(AVERAGE_WHEEL_BASE) / timeInMilliSeconds) * 1000;
        return Double.parseDouble(String.format("%.02f", speed));
    }

    public int getExtractedTime(String record) throws InvalidTimeException {
        try {
            if (record == null)
                return 0;
            return Integer.parseInt(record.substring(1));
        } catch (Exception exception) {
            throw new InvalidTimeException();
        }
    }
}
