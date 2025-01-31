package utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XmlUtils {
    private static final XmlMapper xmlMapper = new XmlMapper();

    // Запись объекта в XML файл
    public static void writeXmlToFile(Object data, String filePath) throws IOException {
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }

    // Чтение XML файла и преобразование в объект
    public static <T> T readXmlFromFile(String filePath, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(new File(filePath), clazz);
    }
}
