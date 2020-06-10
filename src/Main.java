import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String [] args) throws IOException {
        String line = "";
        String splitBy = ",";
        InputStream in = new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv").openStream();
        Files.copy(in, Paths.get("time_series_covid19_confirmed_global.csv"), StandardCopyOption.REPLACE_EXISTING);
            BufferedReader br = new BufferedReader(new FileReader("time_series_covid19_confirmed_global.csv"));
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(splitBy);
                int lastIndex = lines.length - 1;
                int secondToLastIndex = lines.length - 2;
                try {
                    int lastDay = Integer.parseInt(lines[lastIndex]);
                    int secondLastDay = Integer.parseInt(lines[secondToLastIndex]);
                    if (lines[1].contains("Korea")) {
                        System.out.println("Country Name = " + (lines[1].replace("\"", "")) + " South, Number of infected: " + lines[lastIndex] + ", Number of infected last day: " + (lastDay - secondLastDay));
                    }
                    else if (lines[1].contains("Sint")) {
                        System.out.println("Country Name = " + (lines[0].replace("\"", "").concat(lines[1].replace("\"", "")) + ", Number of infected: " + lines[lastIndex] + ", Number of infected last day: " + (lastDay - secondLastDay)));
                    }
                    else {
                        System.out.println("Country Name = " + (lines[1].replace("*", "")) + ", Number of infected: " + lines[lastIndex] + ", Number of infected last day: " + (lastDay - secondLastDay));
                    }
                } catch (NumberFormatException e){
                    e.getMessage();
                }
            }
        }
    }
