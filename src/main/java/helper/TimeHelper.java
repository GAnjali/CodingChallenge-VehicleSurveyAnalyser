package helper;

import exceptions.InvalidTimeException;

public class TimeHelper {
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
}
