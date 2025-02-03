package com.example.task1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FlightTimeReport {
    private String pilotId = "Unknown";
    private String pilotName = "Unknown";
    private Map<String, MonthlyReport> monthlyReports = new HashMap<>();

    public FlightTimeReport(String pilotId, String pilotName) {
        this.pilotId = pilotId;
        this.pilotName = pilotName;
    }

    public void addFlightTime(String month, long flightDuration) {
        MonthlyReport monthlyReport = monthlyReports.getOrDefault(month, new MonthlyReport());
        monthlyReport.addFlightHours(flightDuration);
        monthlyReports.put(month, monthlyReport);
    }

    public void calculateMonthlyMarks() {
        for (MonthlyReport report : monthlyReports.values()) {
            report.calculateMarks();
        }
    }

    public static class MonthlyReport {
        @JsonProperty
        private long totalFlightHours;

        @JsonProperty
        private boolean exceeds80Hours;

        @JsonProperty
        private boolean exceeds36HoursPerWeek;

        @JsonProperty
        private boolean exceeds8HoursPerDay;

        public void addFlightHours(long hours) {
            this.totalFlightHours += hours;
        }

        public void calculateMarks() {
            this.exceeds80Hours = totalFlightHours > FlightTimeConstants.MAX_HOURS_IN_A_MONTH;
            this.exceeds36HoursPerWeek = totalFlightHours > FlightTimeConstants.MAX_HOURS_IN_A_WEEK * 7;
            this.exceeds8HoursPerDay = totalFlightHours > FlightTimeConstants.MAX_HOURS_IN_A_DAY * 30;
        }
    }
}
