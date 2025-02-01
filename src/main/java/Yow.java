import java.io.IOException;
import java.util.Scanner;

public class Yow {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

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
    }

    /**
     * Runs the chatbot to accept user input.
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
     * The main method of the program.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an error occurs while loading or saving tasks.
     */
    public static void main(String[] args) throws IOException, YowException {
        new Yow().run();
    }
}