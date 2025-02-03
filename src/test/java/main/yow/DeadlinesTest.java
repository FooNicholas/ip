package main.yow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import yow.Deadlines;
import yow.YowException;

public class DeadlinesTest {

    @Test
    public void testValidDateParsing() throws YowException {
        Deadlines deadline = new Deadlines("Submit Report", "2025-02-10 1400", false);
        assertEquals("[D][ ] Submit Report (by: Feb 10 2025, 2:00 pm)", deadline.toString());
    }

    @Test
    public void testAlternativeDateFormatParsing() throws YowException {
        Deadlines deadline = new Deadlines("Finish Assignment", "10/2/2025 1400", false);
        assertEquals("[D][ ] Finish Assignment (by: Feb 10 2025, 2:00 pm)", deadline.toString());
    }

    @Test
    public void testInvalidDateFormatThrowsException() {
        Exception exception = assertThrows(YowException.class, () -> {
            new Deadlines("Invalid Date Task", "10-02-2025 14:00", false);
        });
        assertEquals("Invalid date format yow! Use: yyyy-MM-dd HHmm or d/M/yyyy HHmm", exception.getMessage());
    }
}
