package com.example.task1.services;

import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;
import static org.junit.jupiter.api.Assertions.*;

class FlightTimeProcessorTest {
    @Test
    void testCalculateFlightDuration() {
        ZonedDateTime departureTime = ZonedDateTime.parse("2025-02-01T10:00:00+00:00");
        ZonedDateTime arrivalTime = ZonedDateTime.parse("2025-02-01T12:00:00+00:00");

        FlightTimeProcessor processor = new FlightTimeProcessor();
        long duration = processor.calculateFlightDuration(departureTime, arrivalTime);
        assertEquals(120, duration); // 2 часа
    }
}
