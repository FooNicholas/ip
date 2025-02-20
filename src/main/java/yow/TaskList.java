package yow;

import java.util.List;
import java.util.ArrayList;


/**
 * Manages the list of tasks and operations on them.
 */
/**
 * Manages the list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for getTask";
        return tasks.get(index);
    }

    List<Task> getTasks() {
        return tasks;
    }

    public Integer getSize() {
        return tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task to mark as done.
     * @throws YowException If an invalid index is provided.
     */
    public void markTask(int index) throws YowException {
        tasks.get(index).markDone();
    }

    /**
     * Marks a task as not completed.
     *
     * @param index The index of the task to mark as not done.
     * @throws YowException If an invalid index is provided.
     */
    public void unmarkTask(int index) throws YowException {
        tasks.get(index).markUndone();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to delete.
     * @throws YowException If an invalid index is provided.
     */
    public void deleteTask(int index) throws YowException {
        tasks.remove(index);
    }

    /**
     * Converts the task list into a formatted string representation.
     * Each task is listed with its index, status, and description.
     *
     * @return A formatted string containing all tasks.
     */
    String stringifyList() {

        if (tasks.isEmpty()) {
            return "Your task list is empty, yow!";
        }

        StringBuilder listText = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listText.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return listText.toString();
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
