package helper;

import java.util.concurrent.TimeUnit;

import static helper.VehicleSurveyAnalyserConstants.TOTAL_HOURS_PER_DAY;
import static helper.VehicleSurveyAnalyserConstants.TOTAL_MINUTES_PER_HOUR;

public class TimeUtil {
    public long getHoursByDay(int passingTimeInMilliSeconds) {
        return TimeUnit.MILLISECONDS.toHours(passingTimeInMilliSeconds) % TOTAL_HOURS_PER_DAY;
    }

    public long getMinutesByHour(int passingTimeInMilliSeconds) {
        return TimeUnit.MILLISECONDS.toMinutes(passingTimeInMilliSeconds) % TOTAL_MINUTES_PER_HOUR;
    }
}