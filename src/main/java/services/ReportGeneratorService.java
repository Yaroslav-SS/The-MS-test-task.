package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import models.FlightTimeReport;

import java.io.File;
import java.util.Map;

public class ReportGeneratorService {
    private final ObjectMapper jsonMapper;
    private final XmlMapper xmlMapper;

    public ReportGeneratorService() {
        this.jsonMapper = new ObjectMapper(); // Для генерации JSON
        this.xmlMapper = new XmlMapper();    // Для генерации XML
    }


    public void generateJsonReport(Map<String, FlightTimeReport> reports, String filePath) throws Exception {
        jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), reports);
    }  // Генерация JSON с дополнительной информацией о полетах


    public void generateXmlReport(Map<String, FlightTimeReport> reports, String filePath) throws Exception {
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), reports);
    }   // Генерация XML файла
}
