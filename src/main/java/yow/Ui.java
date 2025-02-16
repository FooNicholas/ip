package yow;
/**
 * Handles user interactions, including displaying messages and prompting for input.
 */
public class Ui {

    private StringBuilder responseBuffer = new StringBuilder();

    private void splitLine() {
        System.out.println("____________________________________________________________");
    }



    public void prettyPrint(String text) {
        responseBuffer.append(text).append("\n");
    }

    public String getResponse() {
        String response = responseBuffer.toString();
        responseBuffer.setLength(0); // Clear buffer after returning response
        return response;
    }

    void helpChat() {
        prettyPrint("Available commands:\n"
                + "1. bye - Exits the program.\n"
                + "2. list - Displays the list of all tasks.\n"
                + "3. mark <task_number> - Marks the specified task as done.\n"
                + "4. unmark <task_number> - Marks the specified task as not done.\n"
                + "5. delete <task_number> - Deletes the specified task.\n"
                + "6. todo <description> - Adds a new To-Do task with the given description.\n"
                + "7. deadline <description> /by <date_time> - Adds a new Deadline task with a due date.\n"
                + "8. event <description> /from <start_time> /to <end_time> - Adds a new Event task with timings.\n"
                + "9. within <description> /from <start_time> /to <end_time> - Adds a new task with a completion period.\n");
    }


    void startChat() {
        prettyPrint("Hello! I'm Yow\nWhat can I do for you yow?");
    }

    void endChat() {
        prettyPrint("Bye. Hope to see you again soon yow!");
    }
}