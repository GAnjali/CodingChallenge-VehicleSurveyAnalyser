package helper;

import java.util.concurrent.TimeUnit;

public class TimeUtil {
    public long getDay(int milliseconds) {
        return TimeUnit.MILLISECONDS.toDays(milliseconds);
    }

    public long getHours(int passingTimeInMilliSeconds) {
        return TimeUnit.MILLISECONDS.toHours(passingTimeInMilliSeconds) % 24;
    }

    public long getMinutes(int passingTimeInMilliSeconds) {
        return TimeUnit.MILLISECONDS.toMinutes(passingTimeInMilliSeconds) % 60;
    }
}
