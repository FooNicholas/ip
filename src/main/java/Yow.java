import java.util.*;

public class Yow {
    private ArrayList<Task> checklist;

    public Yow() {
        checklist = new ArrayList<>();
    }

    public void run() {
        Boolean stopCommand = false;
        Scanner scanner = new Scanner(System.in);

        startChat();

        while (!stopCommand) {
            String userInput = scanner.nextLine();

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

                default -> prettyPrint("Please use: todo, deadline, event, list, mark, unmark, or bye yow.");
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
        String listText = "";
        for (int i = 0; i < checklist.size(); i++ ) {
            listText += (i + 1) + "." + checklist.get(i).toString();
            if (i < checklist.size() - 1) {
                listText += "\n";
            }
        }
        return listText;
    }

    public void printMarked(Task targetTask) {
        prettyPrint("Nice! I've marked this task as done yow:\n  "
                + targetTask.toString());
    }

    public void printUnmarked(Task targetTask) {
        prettyPrint("OK, I've marked this task as not done yet yow:\n  "
                + targetTask.toString());
    }

    private void handleMarkCommand(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber >= 0 && taskNumber < checklist.size()) {
                checklist.get(taskNumber).markDone();
                printMarked(checklist.get(taskNumber));
            } else {
                prettyPrint("Invalid task number yow!");
            }
        } else {
            prettyPrint("Invalid command yow! Use 'mark <number>'.");
        }
    }

    private void handleUnmarkCommand(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber >= 0 && taskNumber < checklist.size()) {
                checklist.get(taskNumber).markUndone();
                printUnmarked(checklist.get(taskNumber));
            } else {
                prettyPrint("Invalid task number yow!");
            }
        } else {
            prettyPrint("Invalid command yow! Use 'unmark <number>'.");
        }
    }

    private void handleTodoCommand(String userInput) {
        String description = userInput.substring(5).trim();
        if (!description.isEmpty()) {
            ToDos todo = new ToDos(description);
            checklist.add(todo);
            prettyPrint("Got it. I've added this task:\n  " + todo.toString() +
                    "\nNow you have " + checklist.size() + " tasks in the list.");
        } else {
            prettyPrint("The description of a todo cannot be empty yow!");
        }
    }

    private void handleDeadlineCommand(String userInput) {
        String input = userInput.substring(9).trim();
        String[] parts = input.split(" /by ", 2);
        if (parts.length == 2) {
            Deadlines deadline = new Deadlines(parts[0], parts[1]);
            checklist.add(deadline);
            prettyPrint("Got it. I've added this task:\n  " + deadline.toString() +
                    "\nNow you have " + checklist.size() + " tasks in the list.");
        } else {
            prettyPrint("Invalid format yow! Use: deadline <description> /by <time>");
        }
    }

    private void handleEventCommand(String userInput) {
        String input = userInput.substring(6).trim();
        String[] parts = input.split(" /from ", 2);
        if (parts.length == 2) {
            String[] timeParts = parts[1].split(" /to ", 2);
            if (timeParts.length == 2) {
                Events event = new Events(parts[0], timeParts[0], timeParts[1]);
                checklist.add(event);
                prettyPrint("Got it. I've added this task:\n  " + event.toString() +
                        "\nNow you have " + checklist.size() + " tasks in the list.");
            } else {
                prettyPrint("Invalid format yow! Use: event <description> /from <start time> /to <end time>");
            }
        } else {
            prettyPrint("Invalid format yow! Use: event <description> /from <start time> /to <end time>");
        }
    }


    public static void main(String[] args) {
        Yow yow = new Yow();
        yow.run();
    }
}
