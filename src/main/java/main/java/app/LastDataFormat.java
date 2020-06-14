package main.java.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastDataFormat {

    private static final String OLD_FORMAT = "MM/dd/yy";
    private static final String NEW_FORMAT = "dd.MM.20yy";

    public static String newDate(String lastDate) throws ParseException {
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT); // dodana funkcja zmieniająca format daty z mm/dd/yy na dd.mm.yy
        Date date = sdf.parse(lastDate);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(date);
        return newDateString;
    }
}
