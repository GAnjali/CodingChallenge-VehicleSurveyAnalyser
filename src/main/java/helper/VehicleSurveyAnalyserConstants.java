package helper;

public class VehicleSurveyAnalyserConstants {
    public static final String DATA_FILE = System.getProperty("user.dir") + "/src/main/resources/Vehicle Survey Coding Challenge sample data.txt";
    public static final String REPORT_FOLDER = System.getProperty("user.dir") + "/report";
    public static final String SENSOR1_NAME = "A";
    public static final String SENSOR2_NAME = "B";
    public static final String NO_FILE_FOUND_MESSAGE = "No Such File found, Please provide data file";
    public static final String INVALID_DATA_MESSAGE = "Invalid Data found, Please provide proper data";
    public static final String INVALID_TIME_MESSAGE = "Invalid Time found, Please provide proper data";
    public static final String UNABLE_TO_CREATE_FILE_MESSAGE = "Unable to create file";
    public static final String AVERAGE_WHEEL_BASE = "2.5f";
    public static final String VEHICLE_COUNT_REPORT_FILE_NAME = "VehicleCountReport.txt";
    public static final String PEAK_VOLUME_TIMES_REPORT_FILE_NAME = "PeakVolumeTimesReport.txt";
    public static final String INTER_VEHICULAR_DISTANCE_REPORT_FILE_NAME = "InterVehicularDistanceReport.txt";
    public static final String SPEED_DISTRIBUTION_REPORT_FILE_NAME = "SpeedDistributionReport.txt";
    public static final String VEHICLE_COUNT_REPORT_GENERATOR_HEADING = "***************Vehicle Count Report***************";
    public static final String TOTAL_VEHICLE_COUNT = "\n\t\t\tTotal vehicle count: ";
    public static final String NORTH_VEHICLES_COUNT = "\n\t\t\ttotal vehicle count moving in North direction: ";
    public static final String SOUTH_VEHICLES_COUNT = "\n\t\t\ttotal vehicle count moving in South direction: ";
    public static final String PEAK_VOLUME_TIMES_REPORT_GENERATOR = "***************Peak Volume Times Report***************";
    public static final String MORNING_VS_EVENING_MESSAGE = "\n\t\tMorning Vs Evening:";
    public static final String TOTAL_PEAK_VOLUME_TIMES_MESSAGE = "\t\t\tTotal vehicles in Peak volume time: ";
    public static final String NORTH_VEHICLES_PEAK_VOLUME_TIMES_MESSAGE = "\t\t\ttotal vehicles in peak volume time moving in North direction: ";
    public static final String SOUTH_VEHICLES_PEAK_VOLUME_TIMES_MESSAGE = "\t\t\ttotal vehicles in peak volume time moving in South direction: ";
    public static final String INTER_VEHICULAR_DISTANCE_REPORT_GENERATOR_HEADING = "***************Inter Vehicular Distance Report***************";
    public static final String SPEED_DISTRIBUTION_REPORT_GENERATOR_HEADING = "***************Speed Distribution Report***************";
    public static final String NORTH_BOUND_VEHICLES_MESSAGE = "\n\t\t\t\t North bound vehicles     = ";
    public static final String SOUTH_BOUND_VEHICLES_MESSAGE = "\n\t\t\t\t South bound vehicles     = ";
    public static final String FULL_DAY_REPORT_MESSAGE_TEMPLATE = "\n\tReport on Day - %d";
    public static final String VEHICLE_COUNT_REPORT_PER_DAY_MESSAGE_TEMPLATE = "\n\tTotal vehicle count on day %d = %d";
    public static final String INTER_VEHICULAR_DISTANCE_REPORT_PER_DAY_MESSAGE_TEMPLATE = "\n\tRough distance between vehicles on day %d";
    public static final String TIME_PERIOD_MESSAGE_TEMPLATE = "\n\t\t %s";
    public static final String FROM_TIME_TO_TIME_TEMPLATE = "\n\t\t  From %s to %s";
    public static final int TOTAL_HOURS_PER_DAY = 24;
    public static final int TOTAL_MINUTES_PER_HOUR = 60;
}
