package yow;
/**
 * Handles user interactions, including displaying messages and prompting for input.
 */
class Ui {

    private void splitLine() {
        System.out.println("____________________________________________________________");
    }

    void prettyPrint(String text) {
        splitLine();
        System.out.println(text);
        splitLine();
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
                + "8. event <description> /from <start_time> /to <end_time> - Adds a new Event task with timings.");
    }


    void startChat() {
        prettyPrint("Hello! I'm Yow\nWhat can I do for you yow?");
    }

    void endChat() {
        prettyPrint("Bye. Hope to see you again soon yow!");
    }
}