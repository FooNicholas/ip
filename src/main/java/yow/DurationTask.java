package yow;

import yow.Task;
import yow.YowException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that must be completed within a specific period.
 * Example: "Collect certificate between Jan 15 and 25th".
 */
public class DurationTask extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER2 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a DurationTask with a description, start date, and end date.
     *
     * @param description The task description.
     * @param from The start date of the task.
     * @param to The end date of the task.
     * @param isDone Whether the task is completed.
     */
    public DurationTask(String description, String from, String to, boolean isDone) throws YowException {
        super(description);
        this.isDone = isDone;
        this.start = parseDate(from);
        this.end = parseDate(to);
    }

    private LocalDateTime parseDate(String date) throws YowException {
        try {
            return LocalDateTime.parse(date, INPUT_FORMATTER1);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(date, INPUT_FORMATTER2);
            } catch (DateTimeParseException e2) {
                throw new YowException("Invalid date format yow! Use: yyyy-MM-dd HHmm or d/M/yyyy HHmm");
            }
        }
    }

    /**
     * Converts the DurationTask into a formatted string for file storage.
     *
     * @return A string representation of the task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "W | " + (isDone ? "1" : "0") + " | " + description + " | "
                + start.format(INPUT_FORMATTER1) + " | "
                + end.format(INPUT_FORMATTER1);
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A formatted string representing the DurationTask.
     */
    @Override
    public String toString() {
        return "[W]" + super.toString()
                + " (from: " + start.format(OUTPUT_FORMATTER)
                + " to: " + end.format(OUTPUT_FORMATTER)
                + ")";
    }
}
