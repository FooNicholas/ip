/**
 * Represents an event task with a start and end time.
 * An Events task has a description, completion status, a start time, and an end time.
 */
public class Events extends Task {
    private String from;
    private String to;

    /**
     * Constructs an Events task with a description, start time, and end time.
     *
     * @param description The task description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone Whether the task is marked as completed.
     */
    public Events(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Events task into a formatted string for file storage.
     *
     * @return A string representation of the task suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the Events task.
     *
     * @return A formatted string representing the Events task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}