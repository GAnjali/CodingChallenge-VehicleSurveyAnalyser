package reportGenerator.daywise;

import java.util.concurrent.TimeUnit;

public class TimeUtil {
    public long getHours(int passingTimeInMilliSeconds) {
        return TimeUnit.MILLISECONDS.toHours(passingTimeInMilliSeconds) % 24;
    }

    public long getMinutes(int passingTimeInMilliSeconds) {
        return TimeUnit.MILLISECONDS.toMinutes(passingTimeInMilliSeconds) % 60;
    }
}
