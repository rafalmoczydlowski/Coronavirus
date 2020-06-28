package main.java.app;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    private CSVUtils(){
    }

    public static void downloadCSV(String url, String destination) throws IOException {
        InputStream in = new URL(url).openStream();
        try  {
            Files.copy(in, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    public static List<String[]> readCSV(Reader reader) throws IOException, CsvValidationException {
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

    public static List<String[]> saveTextFile (Reader reader) throws IOException, CsvValidationException, ParseException {
        List<String[]> csvRecordList = CSVUtils.readCSV(reader);
        PrintWriter saveData = new PrintWriter(new FileWriter("Baza danych.txt"));

        int countRecords = 0;
        for(String[] record : csvRecordList){
            // żeby brał tylko nagłówek
            if (countRecords == 0) {
                int lastindex = record.length-1;
                String lastDate = record[lastindex];
                String lastDay = LastDataFormat.newDate(lastDate);
                saveData.println("============== DANE DOTYCZĄCE ZARAŻONYCH KORONAWIRUSEM (STAN NA DZIEŃ " + lastDay + ") ==============");
                saveData.println("");
            }
            //żeby nie pojawiały się nagłówki csv
            if(countRecords>0) {
                int lastIndex = record.length - 1;
                int secondToLastIndex = record.length - 2;
                int lastDay = Integer.parseInt(record[lastIndex]);
                int secondLastDay = Integer.parseInt(record[secondToLastIndex]);
                if (record[1].contains("Korea")) {
                    saveData.print("Country = South Korea");
                } else {
                    saveData.print("Country = " + record[1]);
                }
                if (!record[0].isEmpty()) {
                    saveData.print("/" + record[0]);
                }
                saveData.println("");
                saveData.print("Total number of infected = " + record[lastIndex] + ". Number of infected last day = " + (lastDay - secondLastDay) + '.');
                saveData.println("");
            }
            countRecords++;
        }
        saveData.flush();
        saveData.close();
        return csvRecordList;
    }
}
