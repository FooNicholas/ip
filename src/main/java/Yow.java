import java.util.Scanner;

public class Yow {

    public void run() {
        Boolean stopCommand = false;
        Scanner scanner = new Scanner(System.in);

        startChat();

        while (!stopCommand) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                stopCommand = true;
            } else {
                echo(userInput);
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

    public static void main(String[] args) {
        Yow yow = new Yow();
        yow.run();
    }
}
