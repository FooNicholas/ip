package yow;
/**
 * Represents an abstract task with a description and completion status.
 * This serves as a base class for different types of tasks such as ToDoTask, yow.DeadlineTask, and EventTask.
 */
public abstract class Task {
  protected String description;
  protected boolean isDone;

  /**
   * Constructs a new task with the specified description.
   * By default, a task is not marked as done.
   *
   * @param description The description of the task.
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Marks the task as done.
   */
  public void markDone() {
    isDone = true;
  }

  /**
   * Marks the task as not done.
   */
  public void markUndone() {
    isDone = false;
  }

  /**
   * Converts the task into a formatted string suitable for file storage.
   * Each subclass must implement its own version of this method.
   *
   * @return A string representation of the task formatted for file storage.
   */
  public abstract String toFileFormat();

  /**
   * Returns a string representation of the task.
   * The format includes a checkbox indicating completion status and the task description.
   *
   * @return A formatted string representing the task.
   */
  @Override
  public String toString() {
    String mark = isDone ? "X" : " ";
    return "[" + mark + "] " + description;
  }
}
