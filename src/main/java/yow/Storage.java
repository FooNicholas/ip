package yow;

import yow.Deadlines;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to a file named yow.txt on disk.
 * The file allows for the state of the checklist to be stored across program runs.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = "./data/yow.txt";

    /**
     * Initializes the storage system by ensuring the required file and directory exist.
     *
     * @throws IOException if an error occurs while creating the file or directory.
     */
    public Storage() throws IOException {
        createFileIfNotExists();
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param checklist The list of tasks to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(List<Task> checklist) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : checklist) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

  /**
   * Loads the task list from the storage file.
   *
   * @return A list of tasks retrieved from the file.
   * @throws IOException If an error occurs while reading the file.
   */
  public List<Task> loadTasks() throws IOException, YowException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    private Task parseTaskFromFile(String line) throws YowException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        return switch (type) {
            case "T" -> new ToDos(description, isDone);
            case "D" -> new Deadlines(description, parts[3], isDone);
            case "E" -> new Events(description, parts[3], parts[4], isDone);
            default -> null;
        };
    }

    private void createFileIfNotExists() throws IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
