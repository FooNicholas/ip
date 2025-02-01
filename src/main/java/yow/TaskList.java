package yow;

import java.util.List;

/**
 * Manages the list of tasks and operations on them.
 */
/**
 * Manages the list of tasks.
 */
class TaskList {
    private final List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    Task getTask(int index) {
        return tasks.get(index);
    }

    List<Task> getTasks() {
        return tasks;
    }

    Integer getSize() {
        return tasks.size();
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    void markTask(int index) throws YowException {
        tasks.get(index).markDone();
    }

    void unmarkTask(int index) throws YowException {
        tasks.get(index).markUndone();
    }

    void deleteTask(int index) throws YowException {
        tasks.remove(index);
    }

    String stringifyList() {
        StringBuilder listText = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listText.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return listText.toString();
    }
}
