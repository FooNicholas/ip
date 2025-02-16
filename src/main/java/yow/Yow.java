package yow;

import java.io.IOException;
import java.util.Scanner;


public class Yow {
    private final TaskList taskList;
    private final Storage storage;
    final Ui ui;
    final Parser parser;

    /**
     * Constructor: Initializes the Yow chatbot and loads tasks from storage.
     *
     * @throws IOException If an error occurs while loading the tasks from storage.
     */
    public Yow() throws IOException, YowException {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
        this.ui = new Ui();
        this.parser = new Parser(taskList, storage, ui);

        assert storage != null : "Storage instance should not be null";
        assert taskList != null : "TaskList instance should not be null";
        assert ui != null : "Ui instance should not be null";
        assert parser != null : "Parser instance should not be null";
    }

    /**
     * Runs the chatbot to start accepting user input.
     */
    private void run() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        ui.startChat();

        while (isRunning) {
            String userInput = scanner.nextLine();
            isRunning = parser.parseCommand(userInput);
        }

        ui.endChat();
        scanner.close();
    }

    /**
     * Entry point of the Yow application.
     * Initializes and starts the chatbot for task management.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an error occurs while loading or saving tasks.
     * @throws YowException If an invalid task format is encountered.
     */
    public static void main(String[] args) throws IOException, YowException {
        new Yow().run();
    }
}