package com.example.task1.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.example.task1.models.FlightTimeReport;

import java.io.File;
import java.util.Map;

public class ReportGeneratorService {
    private final ObjectMapper jsonMapper;
    private final XmlMapper xmlMapper;

    public ReportGeneratorService() {
        this.jsonMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();    // для генерации xml, на всякий
    }

    public void generateJsonReport(Map<String, FlightTimeReport> reports, String filePath) throws Exception {
        jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), reports);
    }

    public void generateXmlReport(Map<String, FlightTimeReport> reports, String filePath) throws Exception {
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), reports);
    }   // Генерация XML файла, на всякий
}
