package com.techelevator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static final String FORMAT = "MM/dd/yyyy HH:mm:ss a";

    public static String getCurrentTimeAsString(String format) {
        // LocalDateTime provides a static now() method to get current date/time
        LocalDateTime now = LocalDateTime.now();

        // Create a DateTimeFormatter using a format String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        // format the LocalDateTime into a String
        return formatter.format(now);
    }

}
