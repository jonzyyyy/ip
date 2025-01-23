import java.util.Scanner;

public class Yapper {
    private static Scanner scanner = new Scanner(System.in);
    private static String name = "Yapper";

    public static void main(String[] args) {
        printIntroduction();
        while(true) {
            String request = scanner.nextLine();
            if (request.equals("bye")) {
                break;
            }
            printHorizontalLine();
            System.out.println("\t" + request);
            printHorizontalLine();
        }
        printExit();
        scanner.close();
    }

    public static void printIntroduction() {
        printHorizontalLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        int length = 50;
        String horizontalLine = "-".repeat(length);
        System.out.println("\t" + horizontalLine);
    }

    public static void printExit() {
        printHorizontalLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
