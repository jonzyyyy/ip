package yapper.ui;

public class UI {
    private final String name;
    private static final int HORIZONTAL_LINE_LENGTH = 80;

    public UI(String name) {
        this.name = name;
    }

    public void printIntroduction() {
        printHorizontalLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println("\t" + "-".repeat(HORIZONTAL_LINE_LENGTH));
    }

    public void printExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}