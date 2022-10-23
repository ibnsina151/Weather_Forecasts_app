package ai.munim.testassignment_weatherforecasts.utils;

import org.jetbrains.annotations.Nullable;

public interface Constants {
    float LOCATION_ACCURACY_THRESHOLD = 100f;
    String API_KEY = "NWQ0Mzk3OGVlN2MyNzc5YWZmNjkwYTVkYWE1MjlmOTQ=";
    String DOUBLE_DIGIT_FORMAT = "00";
    long NANO_SECOND = 1000000000;
    long LOCATION_UPDATE_TIME_THRESHOLD = 5; //second

    double kelvin = 273.15;
    String text_week_day_1 = "রবিবার";
    String text_week_day_2 = "সোমবার";
    String text_week_day_3 = "মঙ্গলবার";
    String text_week_day_4 = "বুধবার";
    String text_week_day_5 = "বৃহস্পতিবার";
    String text_week_day_6 = "শুক্রবার";
    String text_week_day_7 = "শনিবার";

    String EMPTY_STRING = "";
    int DEFAULT_INT = 0;
    float DEFAULT_FLOAT = 0f;
    double DEFAULT_DOUBLE = 0.0;

    String day1 = "SUN";
    String day2 = "MON";
    String day3 = "TUE";
    String day4 = "WED";
    String day5 = "THU";
    String day6 = "FRI";
    String day7 = "SAT";

    int hour_in_millis = 60*60*1000;

}
