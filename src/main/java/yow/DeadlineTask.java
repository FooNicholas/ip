package yow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A yow.DeadlineTask task has a description, completion status, and a due date.
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter INPUT_FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER2 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a yow.DeadlineTask task with a description and due date.
     *
     * @param description The task description.
     * @param by The due date for the task.
     * @param isDone Whether the task is marked as completed.
     */
    public DeadlineTask(String description, String by, boolean isDone) throws YowException {
        super(description);
        this.isDone = isDone;
        this.deadline = parseDate(by);
    }

    private LocalDateTime parseDate(String by) throws YowException {
        try {
            return LocalDateTime.parse(by, INPUT_FORMATTER1);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(by, INPUT_FORMATTER2);
            } catch (DateTimeParseException e2) {
                throw new YowException("Invalid date format yow! Use: yyyy-MM-dd HHmm or d/M/yyyy HHmm");
            }
        }
    }

    /**
     * Converts the yow.DeadlineTask task into a formatted string for file storage.
     *
     * @return A string representation of the task suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(INPUT_FORMATTER1);
    }

    /**
     * Returns a string representation of the yow.DeadlineTask task.
     *
     * @return A formatted string representing the DeadlineTask task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMATTER) + ")";
    }
}