package yapper.ui;

public class UI {
    private String name;

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
        int length = 80;
        String horizontalLine = "-".repeat(length);
        System.out.println("\t" + horizontalLine);
    }

    public void printExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

}