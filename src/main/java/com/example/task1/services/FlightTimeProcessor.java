package com.example.task1.services;

import com.example.task1.models.Flight;
import com.example.task1.models.FlightTimeReport;
import com.example.task1.models.Pilot;
import com.example.task1.utils.DateTimeUtils;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightTimeProcessor {

    public Map<String, FlightTimeReport> processFlightData(List<Flight> flights, List<Pilot> pilots) {
        Map<String, FlightTimeReport> reports = new HashMap<>();

        Map<String, String> pilotIdToNameMap = new HashMap<>();
        for (Pilot pilot : pilots) {
            pilotIdToNameMap.put(pilot.getId(), pilot.getName());
        }

        for (Flight flight : flights) {
            for (String pilotId : flight.getCrewIds()) {
                String pilotName = pilotIdToNameMap.getOrDefault(pilotId, "Unknown");

                FlightTimeReport report = reports.getOrDefault(pilotId, new FlightTimeReport(pilotId, pilotName));
                String flightMonth = DateTimeUtils.getMonthYear(flight.getDepartureTime());
                long flightDuration = calculateFlightDuration(flight.getDepartureTime(), flight.getArrivalTime());
                report.addFlightTime(flightMonth, flightDuration);
                reports.put(pilotId, report);
            }
        }

        for (FlightTimeReport report : reports.values()) {
            report.calculateMonthlyMarks();
        }

        return reports;
    }
    long calculateFlightDuration(ZonedDateTime departureTime, ZonedDateTime arrivalTime) {
        return Duration.between(departureTime, arrivalTime).toMinutes();
    }
}

