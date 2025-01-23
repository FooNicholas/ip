import java.util.*;

public class Yow {
    ArrayList<String> checklist;

    public Yow() {
        checklist = new ArrayList<>();
    }

    public void run() {
        Boolean stopCommand = false;
        Scanner scanner = new Scanner(System.in);

        startChat();

        while (!stopCommand) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                stopCommand = true;
            } else if (userInput.equals("list")){
                String listText = "";
                for (int i = 0; i < checklist.size(); i++ ) {
                    listText += (i + 1) + ". " + checklist.get(i);
                    if (i < checklist.size() - 1) {
                        listText += "\n";
                    }
                }
                prettyPrint(listText);
            } else {
                checklist.add(userInput);
                addToListEcho(userInput);
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

    public void addToListEcho(String listItem) {
        prettyPrint("added: " + listItem);
    }

    public static void main(String[] args) {
        Yow yow = new Yow();
        yow.run();
    }
}
