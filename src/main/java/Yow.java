public class Yow {

    public void run() {
        startChat();
        endChat();
    }
    public void splitLine() {
        System.out.println("____________________________________________________________");
    }

    public void startChat() {
        splitLine();
        System.out.println("Hello! I'm Yow\n"
                        + "What can I do for you?");
        splitLine();
    }

    public void endChat() {
        System.out.println("Bye. Hope to see you again soon!");
        splitLine();
    }

    public static void main(String[] args) {
        Yow yow = new Yow();
        yow.run();
    }
}
