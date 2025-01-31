/**
 * Represents a To-Do task.
 * A To-Do is a task with only a description and completion status.
 */
public class ToDos extends Task {
    /**
     * Constructs a To-Do task with a description.
     *
     * @param description The task description.
     * @param isDone Whether the task is marked as completed.
     */
    public ToDos(String description, boolean isDone) {
        super(description);
    }

    /**
     * Converts the To-Do task into a formatted string for file storage.
     *
     * @return A string representation of the task suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the To-Do task.
     *
     * @return A formatted string representing the To-Do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}