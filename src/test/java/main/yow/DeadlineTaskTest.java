package main.yow;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import yow.DeadlineTask;
import yow.YowException;

public class DeadlineTaskTest {

    @Test
    public void testValidDateParsing() throws YowException {
        DeadlineTask deadlineTask = new DeadlineTask("Submit Report", "2025-02-10 1400", false);
        assertEquals("[D][ ] Submit Report (by: Feb 10 2025, 2:00 pm)", deadlineTask.toString());
    }

    @Test
    public void testAlternativeDateFormatParsing() throws YowException {
        DeadlineTask deadlineTask = new DeadlineTask("Finish Assignment", "10/2/2025 1400", false);
        assertEquals("[D][ ] Finish Assignment (by: Feb 10 2025, 2:00 pm)", deadlineTask.toString());
    }

    @Test
    public void testInvalidDateFormatThrowsException() {
        Exception exception = assertThrows(YowException.class, () -> {
            new DeadlineTask("Invalid Date Task", "10-02-2025 14:00", false);
        });
        assertEquals("Invalid date format yow! Use: yyyy-MM-dd HHmm or d/M/yyyy HHmm", exception.getMessage());
    }
}
