package main.java.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String CSV_NAME = "time_series_covid19_confirmed_global.csv";

    public static void main(String [] args) throws Exception {
        String line = "";
        String splitBy = ",";

        CSVUtils.downloadCSV("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",
                CSV_NAME);

        Reader reader = Files.newBufferedReader(Paths.get(CSV_NAME));
        List<String[]> csvRecordList = CSVUtils.readCSV(reader);

        //żeby nie pojawiały się nagłówki csv
        int countRecords = 0;
        for(String[] record : csvRecordList){
            if(countRecords>0) {
                int lastIndex = record.length - 1;
                int secondToLastIndex = record.length - 2;

                int lastDay = Integer.parseInt(record[lastIndex]);
                int secondLastDay = Integer.parseInt(record[secondToLastIndex]);

                System.out.print("Country Name = " + record[1]);
                if (!record[0].isEmpty()) {
                    System.out.print(" / " + record[0]);
                }
                System.out.print(" number of infected: " + record[lastIndex] + ", number of infected last day: " + (lastDay - secondLastDay));

                System.out.println("");
            }
            countRecords++;
        }

//        BufferedReader br = new BufferedReader(new FileReader(CSV_NAME));
//            while ((line = br.readLine()) != null) {
//                String[] lines = line.split(splitBy);
//                int lastIndex = lines.length - 1;
//                int secondToLastIndex = lines.length - 2;
//                try {
//                    int lastDay = Integer.parseInt(lines[lastIndex]);
//                    int secondLastDay = Integer.parseInt(lines[secondToLastIndex]);
//                    if (lines[1].contains("Korea")) {
//                        System.out.println("Country Name = " + (lines[1].replace("\"", "")) + " South, Number of infected: " + lines[lastIndex] + ", Number of infected last day: " + (lastDay - secondLastDay));
//                    }
//                    else if (lines[1].contains("Sint")) {
//                        System.out.println("Country Name = " + (lines[0].replace("\"", "").concat(lines[1].replace("\"", "")) + ", Number of infected: " + lines[lastIndex] + ", Number of infected last day: " + (lastDay - secondLastDay)));
//                    }
//                    else {
//                        System.out.println("Country Name = " + (lines[1].replace("*", "")) + ", Number of infected: " + lines[lastIndex] + ", Number of infected last day: " + (lastDay - secondLastDay));
//                    }
//                } catch (NumberFormatException e){
//                    e.getMessage();
//                }
//            }
        }
    }
