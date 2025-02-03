package com.example.task1.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.task1.models.Flight;
import com.example.task1.models.Pilot;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FileReaderService {
    private final ObjectMapper objectMapper;

    public FileReaderService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Pilot> readPilots(String filePath) throws Exception {
        return objectMapper.readValue(new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Pilot.class));
    }

    public List<Flight> readFlights(String filePath) throws Exception {
        return objectMapper.readValue(new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Flight.class));
    }

}
