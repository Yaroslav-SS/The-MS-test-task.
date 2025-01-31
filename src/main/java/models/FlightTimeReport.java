package models;

import java.util.HashMap;
import java.util.Map;

public class FlightTimeReport {
    private String pilotId;
    private Map<String, MonthlyReport> monthlyReports;

    public FlightTimeReport(String pilotId) {
        this.pilotId = pilotId;
        this.monthlyReports = new HashMap<>();
    }

    public String getPilotId() {
        return pilotId;
    }

    // Метод для добавления полетного времени по месяцу
    public void addFlightTime(String month, long flightDuration) {
        MonthlyReport monthlyReport = monthlyReports.getOrDefault(month, new MonthlyReport());
        monthlyReport.addFlightHours(flightDuration);
        monthlyReports.put(month, monthlyReport);
    }

    // Метод для вычисления отметок
    public void calculateMonthlyMarks() {
        for (MonthlyReport report : monthlyReports.values()) {
            report.calculateMarks();
        }
    }

    // Геттер для отчетов
    public Map<String, MonthlyReport> getMonthlyReports() {
        return monthlyReports;
    }

    // Вложенный класс для хранения информации о полетах за месяц
    public static class MonthlyReport {
        private long totalFlightHours;
        private boolean exceeds80Hours;
        private boolean exceeds36HoursPerWeek;
        private boolean exceeds8HoursPerDay;

        // Метод для добавления полетных часов
        public void addFlightHours(long hours) {
            this.totalFlightHours += hours;
        }

        // Метод для вычисления отметок
        public void calculateMarks() {
            // Проверка на превышение 80 часов в месяц
            this.exceeds80Hours = totalFlightHours > 80;

            // Проверка на превышение 36 часов в неделю (приблизительно 36*7=252 часа в месяц)
            this.exceeds36HoursPerWeek = totalFlightHours > 36 * 7;

            // Проверка на превышение 8 часов в день
            this.exceeds8HoursPerDay = totalFlightHours > 8 * 30; // Принимаем 30 дней в месяце
        }

        // Геттеры для отчетных данных
        public long getTotalFlightHours() {
            return totalFlightHours;
        }

        public boolean isExceeds80Hours() {
            return exceeds80Hours;
        }

        public boolean isExceeds36HoursPerWeek() {
            return exceeds36HoursPerWeek;
        }

        public boolean isExceeds8HoursPerDay() {
            return exceeds8HoursPerDay;
        }
    }
}
