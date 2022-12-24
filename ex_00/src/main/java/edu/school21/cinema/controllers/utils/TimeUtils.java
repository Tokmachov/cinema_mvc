package edu.school21.cinema.controllers.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeUtils {
    public static long parseDateAndTime(String dateAndTime) {
        if (dateAndTime == null) {
            throw new IllegalArgumentException("Date of movie session is not provided.");
        }
        LocalDateTime localDateTime = LocalDateTime.parse(dateAndTime);
        ZoneId zoneId = ZoneId.systemDefault();
        return localDateTime.atZone(zoneId).toEpochSecond();
    }
}
