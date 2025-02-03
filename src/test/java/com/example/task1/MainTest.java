package com.example.task1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.task1.models.FlightTimeReport;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {

    @Test
    public void testJsonReportGeneration() throws Exception {
        // Выполняем основной метод
        Main.main(new String[]{});

        // Проверяем, что файл отчета был создан
        File reportFile = new File("output.json");
        assertTrue(reportFile.exists(), "Файл отчета не был создан!");

        // Проверяем содержимое файла
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, FlightTimeReport> report = objectMapper.readValue(reportFile, Map.class);
        assertNotNull(report, "Содержимое файла отчета не должно быть null!");
        assertTrue(report.containsKey("P001"), "Отчет должен содержать данные для пилота с ID P001!");
    }
}
