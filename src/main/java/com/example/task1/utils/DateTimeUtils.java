package com.example.task1.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String formatDateTime(ZonedDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    public static ZonedDateTime parseDateTime(String dateTimeStr) {
        return ZonedDateTime.parse(dateTimeStr, FORMATTER);
    }

    public static String getMonthYear(ZonedDateTime dateTime) {
        return dateTime.getYear() + "-" + String.format("%02d", dateTime.getMonthValue());
    }
}
