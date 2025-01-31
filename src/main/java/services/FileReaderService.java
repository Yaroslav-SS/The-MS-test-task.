package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Flight;
import models.Pilot;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FileReaderService {
    private final ObjectMapper objectMapper;

    public FileReaderService() {
        this.objectMapper = new ObjectMapper();
        // Регистрация модуля для работы с Java 8 датами и временем
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    // Чтение списка пилотов
    public List<Pilot> readPilots(String filePath) throws Exception {
        return objectMapper.readValue(new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Pilot.class));
    }

    // Чтение списка рейсов
    public List<Flight> readFlights(String filePath) throws Exception {
        return objectMapper.readValue(new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Flight.class));
    }

}
