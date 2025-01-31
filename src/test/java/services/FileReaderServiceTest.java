package services;

import models.Flight;
import models.Pilot;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FileReaderServiceTest {
    @Test
    public void testReadPilots() throws Exception {
        FileReaderService readerService = new FileReaderService();
        List<Pilot> pilots = readerService.readPilots("src/main/resources/pilots.json");

        assertNotNull(pilots);
        assertEquals(2, pilots.size());
        assertEquals("Петр Иванов", pilots.get(0).getName());
    }

    @Test
    public void testReadFlights() throws Exception {
        FileReaderService readerService = new FileReaderService();
        List<Flight> flights = readerService.readFlights("src/main/resources/flights.json");

        assertNotNull(flights);
        assertEquals(2, flights.size());
        assertEquals("Boeing 737", flights.get(0).getAircraftType());
    }
}
