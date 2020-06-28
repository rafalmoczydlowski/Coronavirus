package main.java.app;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class CountryStatistics {

    private static int id;

    public static String getCountryName() { // metoda pobierająca nazwę państwa od użytkownika i wyświetlająca ją na konsoli
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter country name:");
        String countryName = sc.nextLine();
        System.out.println("The country you have chosen is " + countryName.toUpperCase() + ".");
        return countryName;
    }

    public static void displayStatsForCountry(Reader reader) throws CsvValidationException, ParseException, IOException {
        List<String[]> csvRecordList = CSVUtils.saveTextFile(reader);
        String country = CountryStatistics.getCountryName();
        int totalSum = 0;
        for(String[] record : csvRecordList){
            if(record[1].equalsIgnoreCase(country)) {
                int lastIndex = record.length - 1;
                int secondToLastIndex = record.length - 2;
                int lastDay = Integer.parseInt(record[lastIndex]);
                int secondLastDay = Integer.parseInt(record[secondToLastIndex]);
                totalSum = totalSum + lastDay;
                if (record[0].isEmpty()) {
                    System.out.println("COUNTRY " + record[1].toUpperCase() + "\nTotal number of infected = " + record[lastIndex] + ". Number of infected last day = " + (lastDay - secondLastDay) + ".");
                } else {
                    System.out.println(record[0] + " - dependent territory of " + record[1] + "\nTotal number of infected = " + record[lastIndex] + ". Number of infected last day = " + (lastDay - secondLastDay));
                }
            }
            id++;
        }
        System.out.println("\nTotal number of infected in the selected country including dependent territories: " +totalSum);
    }
}
