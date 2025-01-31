import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The Yow chatbot program that manages tasks, including todos, deadlines, and events.
 * It allows users to interact with the task list via commands, and stores tasks persistently using Storage.
 */
public class Yow {
    private List<Task> checklist;
    private final Storage storage;

  /**
   * Constructor: Initializes the Yow chatbot and loads tasks from storage.
   *
   * @throws IOException If an error occurs while loading the tasks from storage.
   */
  public Yow() throws IOException, YowException {
        storage = new Storage();
        checklist = storage.loadTasks();
    }

    private void run() {
        boolean stopCommand = false;
        Scanner scanner = new Scanner(System.in);

        startChat();

        while (!stopCommand) {
            String userInput = scanner.nextLine();
            try {
                switch (userInput.split(" ")[0]) {
                    case "bye" -> stopCommand = true;
                    case "list" -> prettyPrint(stringifyList());
                    case "mark" -> handleMarkCommand(userInput);
                    case "unmark" -> handleUnmarkCommand(userInput);
                    case "todo" -> handleTodoCommand(userInput);
                    case "deadline" -> handleDeadlineCommand(userInput);
                    case "event" -> handleEventCommand(userInput);
                    case "delete" -> handleDeleteCommand(userInput);
                    default -> throw new YowException(
                            "What is blud cooking yow? Use a valid command.\n" +
                                    "Valid commands: bye, list, mark, unmark, todo, deadline, event, delete"
                    );
                }
            } catch (YowException e) {
                prettyPrint("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                prettyPrint("Error: Task number must be a valid integer yow!");
            } catch (IOException e) {
                prettyPrint("Error: Could not save tasks yow!");
            } catch (Exception e) {
                prettyPrint("An unexpected error occurred yow: " + e.getMessage());
            }
        }

        endChat();
        scanner.close();
    }

    private void handleMarkCommand(String userInput) throws YowException, IOException {
        int taskNumber = parseTaskNumber(userInput, "mark");
        checklist.get(taskNumber).markDone();
        storage.saveTasks(checklist);
        prettyPrint("Nice! I've marked this task as done yow:\n  " + checklist.get(taskNumber));
    }

    private void handleUnmarkCommand(String userInput) throws YowException, IOException {
        int taskNumber = parseTaskNumber(userInput, "unmark");
        checklist.get(taskNumber).markUndone();
        storage.saveTasks(checklist);
        prettyPrint("OK, I've marked this task as not done yet yow:\n  " + checklist.get(taskNumber));
    }

    private void handleTodoCommand(String userInput) throws YowException, IOException {
        if (userInput.length() <= 5 || userInput.substring(5).trim().isEmpty()) {
            throw new YowException("OOPS!!! The description of a todo cannot be empty yow!");
        }
        String description = userInput.substring(5).trim();
        Task todo = new ToDos(description, false);
        checklist.add(todo);
        storage.saveTasks(checklist);
        prettyPrint("Got it yow. I've added this task:\n  " + todo);
    }

    private void handleDeadlineCommand(String userInput) throws YowException, IOException {
        String input = parseTaskInput(userInput, "deadline", "/by");
        String[] parts = input.split(" /by ", 2);
        Task deadline = new Deadlines(parts[0], parts[1], false);
        checklist.add(deadline);
        storage.saveTasks(checklist);
        prettyPrint("Got it yow. I've added this task:\n  " + deadline);
    }

    private void handleEventCommand(String userInput) throws YowException, IOException {
        String input = parseTaskInput(userInput, "event", "/from");
        String[] parts = input.split(" /from ", 2);
        String[] timeParts = parts[1].split(" /to ", 2);

        if (timeParts.length != 2) {
            throw new YowException("Invalid format yow! Use: event <description> /from <start time> /to <end time>");
        }

        Task event = new Events(parts[0], timeParts[0], timeParts[1], false);
        checklist.add(event);
        storage.saveTasks(checklist);
        prettyPrint("Got it yow. I've added this task:\n  " + event);
    }

    private void handleDeleteCommand(String userInput) throws YowException, IOException {
        int taskNumber = parseTaskNumber(userInput, "delete");
        Task removedTask = checklist.remove(taskNumber);
        storage.saveTasks(checklist);
        prettyPrint("Noted. I've removed this task yow:\n  " + removedTask);
    }

    private int parseTaskNumber(String userInput, String command) throws YowException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new YowException("Invalid command yow! Use '" + command + " <number>'.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= checklist.size()) {
                throw new YowException("Invalid task number yow!");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new YowException("Invalid number format yow!");
        }
    }

    private String parseTaskInput(String userInput, String command, String delimiter) throws YowException {
        String trim = userInput.substring(command.length()).trim();
        if (userInput.length() <= command.length() || trim.isEmpty()) {
            throw new YowException("OOPS!!! The description of a " + command + " cannot be empty yow!");
        }
        if (!trim.contains(delimiter)) {
            throw new YowException("Invalid format yow! Use: " + command + " <description> " + delimiter + " <time>");
        }
        return trim;
    }

    private String stringifyList() {
        StringBuilder listText = new StringBuilder();
        for (int i = 0; i < checklist.size(); i++) {
            listText.append((i + 1)).append(".").append(checklist.get(i).toString());
            if (i < checklist.size() - 1) {
                listText.append("\n");
            }
        }
        return listText.toString();
    }

    private void prettyPrint(String text) {
        splitLine();
        System.out.println(text);
        splitLine();
    }

    private void splitLine() {
        System.out.println("____________________________________________________________");
    }

    private void startChat() {
        prettyPrint("Hello! I'm Yow\nWhat can I do for you yow?");
    }

    private void endChat() {
        prettyPrint("Bye. Hope to see you again soon yow!");
    }

  /**
   * The main method of the program.
   *
   * @param args Command-line arguments (not used).
   * @throws IOException If an error occurs while loading or saving tasks.
   */
  public static void main(String[] args) throws IOException, YowException {
        Yow yow = new Yow();
        yow.run();
    }
}
