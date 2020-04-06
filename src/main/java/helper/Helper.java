package helper;

import exceptions.InvalidTimeException;

import static constants.VehicleSurveyAnalyserConstants.AVERAGE_WHEEL_BASE;

public class Helper {
    public int calculateDay(int milliseconds) {
        return (int) Math.ceil(((milliseconds * 0.001) / 60 / 60) % 24);
    }

    public int getTime(String record) throws InvalidTimeException {
        try {
            return Integer.parseInt(record.substring(1));
        } catch (Exception exception) {
            throw new InvalidTimeException();
        }
    }

    public float getSpeed(int time) {
        float speed = (Float.parseFloat(AVERAGE_WHEEL_BASE) / time) * 3600;
        return Float.parseFloat(String.format("%.2f", speed));
    }
}
