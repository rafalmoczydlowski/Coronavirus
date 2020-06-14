package main.java.app;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String CSV_NAME = "time_series_covid19_confirmed_global.csv";

    public static void main(String [] args) throws Exception {

        CSVUtils.downloadCSV("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",
                CSV_NAME);

        Reader reader = Files.newBufferedReader(Paths.get(CSV_NAME));
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
    }
}
