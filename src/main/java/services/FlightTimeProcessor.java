package services;

import models.Flight;
import models.FlightTimeReport;
import models.Pilot;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FlightTimeProcessor {

    public Map<String, FlightTimeReport> processFlightData(List<Flight> flights, List<Pilot> pilots) {
        Map<String, FlightTimeReport> reports = new HashMap<>();


        // Перебор всех полетов
        for (Flight flight : flights) {
            // Перебор экипажа для каждого рейса
            for (String pilotId : flight.getCrewIds()) {
                FlightTimeReport report = reports.getOrDefault(pilotId, new FlightTimeReport(pilotId));

                // Определяем месяц рейса
                String flightMonth = getFlightMonth(flight.getDepartureTime());

                // Рассчитываем полетное время для этого рейса
                long flightDuration = calculateFlightDuration(flight.getDepartureTime(), flight.getArrivalTime());

                // Добавляем полетное время в отчет для данного пилота за месяц
                report.addFlightTime(flightMonth, flightDuration);

                reports.put(pilotId, report);
            }
        }

        // Вычисление отметок для каждого специалиста
        for (FlightTimeReport report : reports.values()) {
            report.calculateMonthlyMarks();
        }

        return reports;
    }

    // Получаем месяц в формате "YYYY-MM"
    private String getFlightMonth(LocalDateTime departureTime) {
        return departureTime.getYear() + "-" + String.format("%02d", departureTime.getMonthValue());
    }

    // Рассчитываем продолжительность полета в минутах
    private long calculateFlightDuration(LocalDateTime departureTime, LocalDateTime arrivalTime) {
        return ChronoUnit.MINUTES.between(departureTime, arrivalTime);
    }
}
