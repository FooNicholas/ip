package yow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end time.
 * An Events task has a description, completion status, a start time, and an end time.
 */
public class Events extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER2 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs an Events task with a description, start time, and end time.
     *
     * @param description The task description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone Whether the task is marked as completed.
     */
    public Events(String description, String from, String to, boolean isDone) throws YowException {
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
     * Converts the Events task into a formatted string for file storage.
     *
     * @return A string representation of the task suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + start.format(INPUT_FORMATTER1) + " | "
                + end.format(INPUT_FORMATTER1);
    }

    /**
     * Returns a string representation of the Events task.
     *
     * @return A formatted string representing the Events task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(OUTPUT_FORMATTER)
                + " to: " + end.format(OUTPUT_FORMATTER)
                + ")";
    }
}