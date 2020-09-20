package com.luizfrra.stockSim.Utils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;

public class DateStockUtils {

    public static int MONDAY = 1;
    public static int FRIDAY = 5;
    public static int NUMBER_OF_SECONDS_IN_A_HOUR = 3600;
    public static int NUMBER_OF_SECONDS_IN_A_MINUTE = 60;
    public static int SECONDS_PASSED_AFTER_10H = 36000;
    public static int SECONDS_PASSED_AFTER_17H_30M = 63000;

    public static boolean isWeekDay(Instant instant) {
        DayOfWeek dayOfWeek = instant.atZone(ZoneId.systemDefault()).getDayOfWeek();
        return (dayOfWeek.getValue() >= MONDAY && dayOfWeek.getValue() <= FRIDAY);
    }

    public static boolean isBusinessHour(Instant instant) {
        int hour = instant.atZone(ZoneId.systemDefault()).getHour();
        int minutes = instant.atZone(ZoneId.systemDefault()).getMinute();
        int secondsPassed = (hour * NUMBER_OF_SECONDS_IN_A_HOUR) + (minutes * NUMBER_OF_SECONDS_IN_A_MINUTE);
        return (secondsPassed >= SECONDS_PASSED_AFTER_10H && secondsPassed <= SECONDS_PASSED_AFTER_17H_30M);
    }
}
