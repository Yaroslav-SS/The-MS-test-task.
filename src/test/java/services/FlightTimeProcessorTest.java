package services;

import models.Flight;
import models.FlightTimeReport;
import models.Pilot;
import org.junit.jupiter.api.Test;
import services.FlightTimeProcessor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTimeProcessorTest {

    @Test
    public void testProcessFlightData() {
        // Создаем тестовых пилотов
        Pilot pilot1 = new Pilot("P001", "Арсений Попов");
        Pilot pilot2 = new Pilot("P002", "Василий Макунин");

        List<Pilot> pilots = Arrays.asList(pilot1, pilot2);

        // Создаем тестовые рейсы
        Flight flight1 = new Flight(
                "Boeing 737", "AA321",
                LocalDateTime.of(2025, 1, 1, 8, 0),
                LocalDateTime.of(2025, 1, 1, 12, 0),
                "JFK", "LAX",
                Arrays.asList("P001", "P002")
        );

        Flight flight2 = new Flight(
                "Airbus A320", "BA475",
                LocalDateTime.of(2025, 1, 2, 10, 0),
                LocalDateTime.of(2025, 1, 2, 14, 30),
                "LAX", "ORD",
                Arrays.asList("P002")
        );

        Flight flight3 = new Flight(
                "Boeing 747", "CA900",
                LocalDateTime.of(2025, 1, 3, 9, 0),
                LocalDateTime.of(2025, 1, 3, 19, 30), // 10.5 часов
                "ORD", "JFK",
                Arrays.asList("P002")
        );

        List<Flight> flights = Arrays.asList(flight1, flight2, flight3);

        // Создаем процессор и обрабатываем данные
        FlightTimeProcessor processor = new FlightTimeProcessor();
        Map<String, FlightTimeReport> reports = processor.processFlightData(flights, pilots);

        // Проверяем, что отчеты были созданы
        assertNotNull(reports);
        assertEquals(2, reports.size());

        // Проверяем отчет по пилоту P001 (только 4 часа)
        FlightTimeReport reportPilot1 = reports.get("P001");
        assertNotNull(reportPilot1);
        assertEquals(240, reportPilot1.getMonthlyReports().get("2025-01").getTotalFlightHours());
        assertFalse(reportPilot1.getMonthlyReports().get("2025-01").isExceeds80Hours());
        assertFalse(reportPilot1.getMonthlyReports().get("2025-01").isExceeds36HoursPerWeek());
        assertFalse(reportPilot1.getMonthlyReports().get("2025-01").isExceeds8HoursPerDay());

        // Проверяем отчет по пилоту P002 (4 + 4.5 + 10.5 = 19 часов)
        FlightTimeReport reportPilot2 = reports.get("P002");
        assertNotNull(reportPilot2);
        assertEquals(1140, reportPilot2.getMonthlyReports().get("2025-01").getTotalFlightHours());
        assertTrue(reportPilot2.getMonthlyReports().get("2025-01").isExceeds8HoursPerDay());
        assertFalse(reportPilot2.getMonthlyReports().get("2025-01").isExceeds80Hours());
        assertFalse(reportPilot2.getMonthlyReports().get("2025-01").isExceeds36HoursPerWeek());
    }
}
