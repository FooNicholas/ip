public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public void markDone() {
      isDone = true;
  }
  public void markUndone() {
      isDone = false;
  }

  @Override
  public String toString() {
    String mark = isDone ? "X" : " ";
    return "[" + mark + "] " + description;
  }
}
