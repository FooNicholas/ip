import java.util.*;

public class Yow {
    private ArrayList<Task> checklist;

    public Yow() {
        checklist = new ArrayList<>();
    }

    public void run() {
        boolean stopCommand = false;
        Scanner scanner = new Scanner(System.in);

        startChat();

        while (!stopCommand) {
            String userInput = scanner.nextLine();

            try {
                switch (userInput.split(" ")[0]) {
                    case "bye" -> stopCommand = true;

                    case "list" -> {
                        String listText = stringifyList();
                        prettyPrint(listText);
                    }

                    case "mark" -> handleMarkCommand(userInput);

                    case "unmark" -> handleUnmarkCommand(userInput);

                    case "todo" -> handleTodoCommand(userInput);

                    case "deadline" -> handleDeadlineCommand(userInput);

                    case "event" -> handleEventCommand(userInput);

                    case "delete" -> handleDeleteCommand(userInput);

                    default -> throw new YowException("What is bro cooking yow. Use a valid command.\n" +
                            "Valid commands: bye, list, mark, unmark, todo, deadline, event, delete");
                }
            } catch (YowException e) {
                prettyPrint("Error: " + e.getMessage());
            } catch (Exception e) {
                prettyPrint("An unexpected error occurred yow: " + e.getMessage());
            }
        }

        endChat();
        scanner.close();
    }

    public void splitLine() {
        System.out.println("____________________________________________________________");
    }

    public void prettyPrint(String text) {
        splitLine();
        System.out.println(text);
        splitLine();
    }

    public void startChat() {
        prettyPrint("Hello! I'm Yow\n"
                + "What can I do for you yow?");
    }

    public void endChat() {
        prettyPrint("Bye. Hope to see you again soon yow!");
    }

    public String stringifyList() {
        StringBuilder listText = new StringBuilder();
        for (int i = 0; i < checklist.size(); i++) {
            listText.append((i + 1)).append(".").append(checklist.get(i).toString());
            if (i < checklist.size() - 1) {
                listText.append("\n");
            }
        }
        return listText.toString();
    }

    public void printMarked(Task targetTask) {
        prettyPrint("Nice! I've marked this task as done yow:\n  "
                + targetTask.toString());
    }

    public void printUnmarked(Task targetTask) {
        prettyPrint("OK, I've marked this task as not done yet yow:\n  "
                + targetTask.toString());
    }

    private void handleMarkCommand(String userInput) throws YowException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new YowException("Invalid command yow! Use 'mark <number>'.");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= checklist.size()) {
            throw new YowException("Invalid task number yow!");
        }
        checklist.get(taskNumber).markDone();
        printMarked(checklist.get(taskNumber));
    }

    private void handleUnmarkCommand(String userInput) throws YowException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new YowException("Invalid command yow! Use 'unmark <number>'.");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= checklist.size()) {
            throw new YowException("Invalid task number yow!");
        }
        checklist.get(taskNumber).markUndone();
        printUnmarked(checklist.get(taskNumber));
    }

    private void handleTodoCommand(String userInput) throws YowException {
        if (userInput.length() <= 5 || userInput.substring(5).trim().isEmpty()) {
            throw new YowException("OOPS!!! The description of a todo cannot be empty yow!");
        }
        String description = userInput.substring(5).trim();
        ToDos todo = new ToDos(description);
        checklist.add(todo);
        prettyPrint("Got it yow. I've added this task:\n  " + todo.toString() +
                "\nNow you have " + checklist.size() + " tasks in the list.");
    }

    private void handleDeadlineCommand(String userInput) throws YowException {
        if (userInput.length() <= 9 || userInput.substring(9).trim().isEmpty()) {
            throw new YowException("OOPS!!! The description of a deadline cannot be empty yow!");
        }
        String input = userInput.substring(9).trim();
        String[] parts = input.split(" /by ", 2);
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new YowException("Invalid format yow! Use: deadline <description> /by <time>");
        }
        Deadlines deadline = new Deadlines(parts[0], parts[1]);
        checklist.add(deadline);
        prettyPrint("Got it yow. I've added this task:\n  " + deadline.toString() +
                "\nNow you have " + checklist.size() + " tasks in the list.");
    }

    private void handleEventCommand(String userInput) throws YowException {
        if (userInput.length() <= 6 || userInput.substring(6).trim().isEmpty()) {
            throw new YowException("OOPS!!! The description of an event cannot be empty yow!");
        }
        String input = userInput.substring(6).trim();
        String[] parts = input.split(" /from ", 2);
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new YowException("Invalid format yow! Use: event <description> /from <start time> /to <end time>");
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length != 2 || timeParts[0].isEmpty() || timeParts[1].isEmpty()) {
            throw new YowException("Invalid format yow! Use: event <description> /from <start time> /to <end time>");
        }
        Events event = new Events(parts[0], timeParts[0], timeParts[1]);
        checklist.add(event);
        prettyPrint("Got it yow. I've added this task:\n  " + event.toString() +
                "\nNow you have " + checklist.size() + " tasks in the list.");
    }

    private void handleDeleteCommand(String userInput) throws YowException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new YowException("Invalid command yow! Use 'delete <number>'.");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= checklist.size()) {
            throw new YowException("Invalid task number yow!");
        }
        Task removedTask = checklist.remove(taskNumber);
        prettyPrint("Noted. I've removed this task yow:\n  " + removedTask.toString() +
                "\nNow you have " + checklist.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Yow yow = new Yow();
        yow.run();
    }
}