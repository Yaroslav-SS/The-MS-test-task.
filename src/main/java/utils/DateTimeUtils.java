package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Метод для форматирования даты в строку
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    // Метод для парсинга строки в LocalDateTime
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, FORMATTER);
    }

    // Получение месяца в формате "YYYY-MM"
    public static String getMonthYear(LocalDateTime dateTime) {
        return dateTime.getYear() + "-" + String.format("%02d", dateTime.getMonthValue());
    }
}
