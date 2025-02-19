package yow;

import java.util.List;

/**
 * Parses user input and delegates commands accordingly.
 */
class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    boolean parseCommand(String userInput) {

        try {
            switch (userInput.split(" ")[0]) {
                case "bye":
                    return false;
                case "list":
                    ui.prettyPrint(taskList.stringifyList());
                    break;
                case "help" :
                    ui.helpChat();
                    break;
                case "mark":
                    handleMarkCommand(userInput);
                    break;
                case "unmark":
                    handleUnmarkCommand(userInput);
                    break;
                case "delete":
                    handleDeleteCommand(userInput);
                    break;
                case "todo":
                    handleTodoCommand(userInput);
                    break;
                case "deadline":
                    handleDeadlineCommand(userInput);
                    break;
                case "event":
                    handleEventCommand(userInput);
                    break;
                case "within":
                    handleWithinCommand(userInput);
                    break;
                case "find":
                    handleFindCommand(userInput);
                    break;

            default:
                    ui.prettyPrint("Unknown command yow! Type in 'help' to get command list.");
            }
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.prettyPrint("Error: " + e.getMessage());
        }
        return true;
    }

    private int parseTaskNumber(String userInput, String command) throws YowException {
        String[] parts = userInput.split(" ");
        assert parts.length > 0 : "User input cannot be empty";

        if (parts.length != 2) {
            throw new YowException("Invalid command yow! Use '" + command + " <number>'.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            assert taskNumber >= 0 : "Parsed task number should be non-negative";

            if (taskNumber >= taskList.getSize()) {
                throw new YowException("Invalid task number yow! Ensure it is within the list range.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new YowException("Invalid number format yow! Type in 'help' to get command list.");
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

    private void handleMarkCommand(String userInput) throws YowException {
        int taskNumber = parseTaskNumber(userInput, "mark");
        Task selectedTask = taskList.getTask(taskNumber);
        taskList.markTask(taskNumber);
        ui.prettyPrint("Nice! I've marked this task as done yow:\n  " + selectedTask);
    }

    private void handleUnmarkCommand(String userInput) throws YowException {
        int taskNumber = parseTaskNumber(userInput, "unmark");
        Task selectedTask = taskList.getTask(taskNumber);
        taskList.unmarkTask(taskNumber);
        ui.prettyPrint("OK, I've marked this task as not done yet yow:\n  " + selectedTask);
    }

    private void handleTodoCommand(String userInput) throws YowException {
        String input = parseTaskInput(userInput, "todo", "");
        Task todo = new ToDoTask(input, false);
        taskList.addTask(todo);
        ui.prettyPrint("Got it yow. I've added this task:\n  " + todo);
    }

    private void handleDeadlineCommand(String userInput) throws YowException {
        String input = parseTaskInput(userInput, "deadline", "/by");
        String[] parts = input.split(" /by ", 2);
        Task deadline = new DeadlineTask(parts[0], parts[1], false);
        taskList.addTask(deadline);
        ui.prettyPrint("Got it yow. I've added this task:\n  " + deadline);
    }

    private void handleEventCommand(String userInput) throws YowException {
        String input = parseTaskInput(userInput, "event", "/from");
        String[] parts = input.split(" /from ", 2);
        String[] timeParts = parts[1].split(" /to ", 2);

        if (timeParts.length != 2) {
            throw new YowException("Invalid format yow! Use: event <description> /from <start time> /to <end time>");
        }

        Task event = new EventTask(parts[0], timeParts[0], timeParts[1], false);
        taskList.addTask(event);
        ui.prettyPrint("Got it yow. I've added this task:\n  " + event);
    }

    private void handleDeleteCommand(String userInput) throws YowException {
        int taskNumber = parseTaskNumber(userInput, "delete");
        String selectedTask = taskList.getTask(taskNumber).toString();
        taskList.deleteTask(taskNumber);
        ui.prettyPrint("Noted. I've removed this task yow:\n  " + selectedTask);

    }

    private void handleFindCommand(String userInput) throws YowException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new YowException("OOPS!!! You must provide a keyword to search yow!");
        }

        String keyword = parts[1].trim();
        List<Task> matchingTasks = taskList.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            ui.prettyPrint("No matching tasks found yow!");
        } else {
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list yow:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            ui.prettyPrint(response.toString());
        }
    }

    private void handleWithinCommand(String userInput) throws YowException {
        String input = parseTaskInput(userInput, "within", "/from");
        String[] parts = input.split(" /from ", 2);
        String[] timeParts = parts[1].split(" /to ", 2);

        if (timeParts.length != 2) {
            throw new YowException("Invalid format yow! Use: within <description> /from <start date> /to <end date>");
        }

        Task withinTask = new DurationTask(parts[0], timeParts[0], timeParts[1], false);
        taskList.addTask(withinTask);
        ui.prettyPrint("Got it yow. I've added this task:\n  " + withinTask);
    }


}