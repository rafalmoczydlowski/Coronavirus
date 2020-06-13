package main.java.app;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static void downloadCSV(String url, String destination){
        try {
            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new RuntimeException("Shit happens");
        }
    }

    public static List<String[]> readCSV(Reader reader) throws Exception {
        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            list.add(line);
        }
        reader.close();
        csvReader.close();
        return list;
    }

}
