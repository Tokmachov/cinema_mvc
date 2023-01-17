package edu.school21.cinema.controllers.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    public static long parseDateAndTime(String dateAndTime) {
        if (dateAndTime == null) {
            throw new IllegalArgumentException("Date of movie session is not provided.");
        }
        LocalDateTime localDateTime = LocalDateTime.parse(dateAndTime);
        ZoneId zoneId = ZoneId.systemDefault();
        return localDateTime.atZone(zoneId).toEpochSecond();
    }

    public static Pair<String, String> toDateAndTime(long timeStamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd - MM - yyyy");
        String date = localDateTime.format(dateFormatter);
        String time = localDateTime.format(timeFormatter);
        return Pair.of(date, time);
    }
}
