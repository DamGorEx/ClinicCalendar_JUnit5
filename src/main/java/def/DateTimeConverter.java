package def;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeConverter {
    static LocalDateTime convertToDateTimeFromString(String dateTime, LocalDate today) {
        LocalDateTime localDateTime;
        try {
            String dateTimeString = dateTime.toLowerCase();
            if (dateTimeString.contains("today")) {
                String parsedDateTime = dateTimeString.split("\\s*today\\s*")[1].toUpperCase();
                LocalTime visitTime = LocalTime.parse(  parsedDateTime,
                        DateTimeFormatter.ofPattern("h:mm a"));
                String todayStringToConc = today.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
                String visitTimeToConc = visitTime.format(DateTimeFormatter.ofPattern("h:mm a"));
                dateTime =  todayStringToConc + " " + visitTimeToConc;
            }
            localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
                    DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.getDefault()));

        } catch (Throwable t) {
            throw new RuntimeException("Unable to create date time: " + dateTime +
                    ", please enter with format: [M/d/yyyy h:mm a]");
        }
        return localDateTime;
    }
}
