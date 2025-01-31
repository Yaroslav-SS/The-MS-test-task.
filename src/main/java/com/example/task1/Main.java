package com.example.task1;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Flight;
import models.FlightTimeReport;
import models.Pilot;
import services.FlightTimeProcessor;
import services.ReportGeneratorService;
import utils.JsonUtils;

import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        try {
            // 1. Чтение данных
            List<Pilot> pilots = JsonUtils.readJsonFromFile("src/main/resources/pilots.json", new TypeReference<List<Pilot>>() {});
            List<Flight> flights = JsonUtils.readJsonFromFile("src/main/resources/flights.json", new TypeReference<List<Flight>>() {});

            // 2. Обработка данных
            FlightTimeProcessor processor = new FlightTimeProcessor();
            Map<String, FlightTimeReport> reports = processor.processFlightData(flights, pilots);



            // 3. Генерация отчета
            String outputFormat = "JSON"; // Или "XML"
            ReportGeneratorService reportGeneratorService = new ReportGeneratorService();
            if (outputFormat.equals("JSON")) {
                reportGeneratorService.generateJsonReport(reports, "output.json");
            } else if (outputFormat.equals("XML")) {
                reportGeneratorService.generateXmlReport(reports, "output.xml");
            }


            System.out.println("Отчет успешно сгенерирован!");
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
