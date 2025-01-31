package com.example.task1;

import com.example.task1.Main;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    public void testJsonReportGeneration() throws Exception {
        Main.main(new String[]{}); // Запускаем приложение

        File reportFile = new File("flight_time_report.json");
        assertTrue(reportFile.exists()); // Проверяем, что файл создан
    }
}
