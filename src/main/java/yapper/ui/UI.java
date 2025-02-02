package yapper.ui;

/**
 * Handles the user interface for the Yapper chatbot,
 * including displaying messages and formatting output.
 */
public class UI {
    private String name;

    /**
     * Constructs a {@code UI} instance with the chatbot's name.
     *
     * @param name The name of the chatbot.
     */
    public UI(String name) {
        this.name = name;
    }

    /**
     * Prints the chatbot's introduction message.
     */
    public void printIntroduction() {
        printHorizontalLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints a horizontal line for visual separation in the console.
     */
    public void printHorizontalLine() {
        int length = 80;
        String horizontalLine = "-".repeat(length);
        System.out.println("\t" + horizontalLine);
    }

    /**
     * Prints the chatbot's exit message.
     */
    public void printExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}