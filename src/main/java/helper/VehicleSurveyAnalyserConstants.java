package helper;

public class VehicleSurveyAnalyserConstants {
    //PATH
    public static final String DATA_FILE = System.getProperty("user.dir") + "/src/main/resources/Vehicle Survey Coding Challenge sample data.txt";
    public static final String REPORT_FOLDER = System.getProperty("user.dir") + "/report";
    //SENSORS
    public static final String SENSOR1_NAME = "A";
    public static final String SENSOR2_NAME = "B";
    //CONSTANT (MAGIC NUMBERS)
    public static final String AVERAGE_WHEEL_BASE = "2.5f";
    public static final int TOTAL_HOURS_PER_DAY = 24;
    public static final int TOTAL_MINUTES_PER_HOUR = 60;
    public static final int DISTRIBUTION_GAP = 5;
    //EXCEPTION MESSAGES
    public static final String NO_FILE_FOUND_MESSAGE = "No Such File found, Please provide data file";
    public static final String INVALID_DATA_MESSAGE = "Invalid Data found, Please provide proper data\nException occurred while parsing the Record ";
    public static final String INVALID_TIME_MESSAGE = "Invalid Time found, Please provide proper data";
    public static final String UNABLE_TO_CREATE_FILE_MESSAGE = "Unable to create file";
    //REPORT FILE NAMES
    public static final String VEHICLE_COUNT_REPORT_FILE_NAME = "VehicleCountReport.txt";
    public static final String PEAK_VOLUME_TIMES_REPORT_FILE_NAME = "PeakVolumeTimesReport.txt";
    public static final String INTER_VEHICULAR_DISTANCE_REPORT_FILE_NAME = "InterVehicularDistanceReport.txt";
    public static final String SPEED_DISTRIBUTION_REPORT_FILE_NAME = "SpeedDistributionReport.txt";
    //REPORT MESSAGE TEMPLATES
    public static final String VEHICLE_COUNT_REPORT_GENERATOR_HEADING_TEMPLATE = "***************Vehicle Count Report***************";
    public static final String PEAK_VOLUME_TIMES_REPORT_GENERATOR_TEMPLATE = "***************Peak Volume Times Report***************";
    public static final String INTER_VEHICULAR_DISTANCE_REPORT_GENERATOR_HEADING_TEMPLATE = "***************Inter Vehicular Distance Report***************";
    public static final String SPEED_DISTRIBUTION_REPORT_GENERATOR_HEADING_TEMPLATE = "***************Speed Distribution Report***************";
    public static final String TOTAL_VEHICLE_COUNT_MESSAGE_TEMPLATE = "\n\t\t\tTotal vehicle count: ";
    public static final String NORTH_VEHICLES_COUNT_MESSAGE_TEMPLATE = "\n\t\t\ttotal vehicle count moving in North direction: ";
    public static final String SOUTH_VEHICLES_COUNT_MESSAGE_TEMPLATE = "\n\t\t\ttotal vehicle count moving in South direction: ";
    public static final String MORNING_VS_EVENING_MESSAGE_TEMPLATE = "\n\t\tMorning Vs Evening:";
    public static final String TOTAL_PEAK_VOLUME_TIMES_MESSAGE_TEMPLATE = "\t\t\tTotal vehicles in Peak volume time: ";
    public static final String NORTH_VEHICLES_PEAK_VOLUME_TIMES_MESSAGE_TEMPLATE = "\t\t\ttotal vehicles in peak volume time moving in North direction: ";
    public static final String SOUTH_VEHICLES_PEAK_VOLUME_TIMES_MESSAGE_TEMPLATE = "\t\t\ttotal vehicles in peak volume time moving in South direction: ";
    public static final String NORTH_BOUND_VEHICLES_MESSAGE_TEMPLATE = "\n\t\t\t\t North bound vehicles     = ";
    public static final String SOUTH_BOUND_VEHICLES_MESSAGE_TEMPLATE = "\n\t\t\t\t South bound vehicles     = ";
    public static final String FULL_DAY_REPORT_MESSAGE_TEMPLATE = "\n\tReport on Day - %s";
    public static final String VEHICLE_COUNT_REPORT_PER_DAY_MESSAGE_TEMPLATE = "\n\tTotal vehicle count on %s = %d";
    public static final String INTER_VEHICULAR_DISTANCE_REPORT_PER_DAY_MESSAGE_TEMPLATE = "\n\tRough distance between vehicles on %s";
    public static final String TIME_PERIOD_MESSAGE_TEMPLATE = "\n\t\t %s";
    public static final String FROM_TIME_TO_TIME_TEMPLATE = "\n\t\t  From %s to %s";
    public static final String FROM_SPEED_TO_SPEED_TEMPLATE = "\n\t\t  Speed From %s to %s = %s%%";
}
