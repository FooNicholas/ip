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

                case "mark" -> {
                    String[] parts = userInput.split(" ");
                    if (parts.length == 2) {
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (taskNumber >= 0 && taskNumber < checklist.size()) {
                            checklist.get(taskNumber).markDone();
                            printMarked(checklist.get(taskNumber));
                        } else {
                            prettyPrint("Invalid task number!");
                        }
                    } else {
                        prettyPrint("Invalid command! Use 'mark <number>'.");
                    }
                }

                case "unmark" -> {
                    String[] parts = userInput.split(" ");
                    if (parts.length == 2) {
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (taskNumber >= 0 && taskNumber < checklist.size()) {
                            checklist.get(taskNumber).markUndone();
                            printUnmarked(checklist.get(taskNumber));
                        } else {
                            prettyPrint("Invalid task number!");
                        }
                    } else {
                        prettyPrint("Invalid command! Use 'unmark <number>'.");
                    }
                }

                default -> {
                    Task freshTask = new Task(userInput);
                    checklist.add(freshTask);
                    addToListEcho(userInput);
                }
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

    public void echo(String input) {
        prettyPrint(input);
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

    public void addToListEcho(String listItem) {
        prettyPrint("added: " + listItem);
    }

    public void printMarked(Task targetTask) {
        prettyPrint("Nice! I've marked this task as done yow:\n  "
                + targetTask.toString());
    }

    public void printUnmarked(Task targetTask) {
        prettyPrint("OK, I've marked this task as not done yet yow:\n  "
                + targetTask.toString());
    }

    public static void main(String[] args) {
        Yow yow = new Yow();
        yow.run();
    }
}
