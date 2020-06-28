package main.java.app;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String CSV_NAME = "time_series_covid19_confirmed_global.csv";

    public static void main(String [] args) throws Exception {

        CSVUtils.downloadCSV("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",
                CSV_NAME);

        Reader reader = Files.newBufferedReader(Paths.get(CSV_NAME));
        CountryStatistics.displayStatsForCountry(reader);
    }
}
