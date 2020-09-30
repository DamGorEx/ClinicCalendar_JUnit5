package def;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("DateTimeConverter should")
class DateTimeConverterTest {

    @Test
    @Tag("dateTime ")
    @DisplayName("throw exception when wrong string date format provided with specific error msg")
    void throwExceptionIfIncorrectPatternProvided() {

        Throwable error = assertThrows(RuntimeException.class, () -> {
            DateTimeConverter.convertToDateTimeFromString("9/2/2018xxx1:00 PM", LocalDate.now());
        });
        assertEquals("Unable to create date time: 9/2/2018xxx1:00 PM, please enter with format: [M/d/yyyy h:mm a]"
                    , error.getMessage());
    }
    @Test
    @DisplayName("conver string to correct date")
    private void convertCorrectPatternToDateTime() {
        LocalDateTime res1 = DateTimeConverter.convertToDateTimeFromString("9/2/2018 1:00 PM", LocalDate.now());
        assertEquals(res1, LocalDateTime.of(2018,9,2,13,0));
    }

    @Nested
    @DisplayName("conver string with word 'today' and replace it with proper date ")
    class todayTests {
        @Test
        @DisplayName("correctly")
        void convertTodayStringCorrectly() {
            LocalDateTime res = DateTimeConverter.convertToDateTimeFromString("today 1:00 PM", LocalDate.now());
            assertEquals(res, LocalDateTime.of(LocalDate.now(),
                    LocalTime.parse("1:00 PM", DateTimeFormatter.ofPattern("h:mm a"))));
        }

        @Test
        @DisplayName("regaldless of case")
        void convertTodayStringCorrectlyCaseInsensitive() {
            LocalDateTime res = DateTimeConverter.convertToDateTimeFromString("ToDay 1:00 PM", LocalDate.now());
            assertEquals(res, LocalDateTime.of(LocalDate.now(),
                    LocalTime.parse("1:00 PM", DateTimeFormatter.ofPattern("h:mm a"))));
        }
    }
}