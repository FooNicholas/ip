/**
 * Represents a task with a deadline.
 * A Deadlines task has a description, completion status, and a due date.
 */
public class Deadlines extends Task {
    private String by;

    /**
     * Constructs a Deadlines task with a description and due date.
     *
     * @param description The task description.
     * @param by The due date for the task.
     * @param isDone Whether the task is marked as completed.
     */
    public Deadlines(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadlines task into a formatted string for file storage.
     *
     * @return A string representation of the task suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of the Deadlines task.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}