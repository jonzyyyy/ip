import java.util.Scanner;
import java.util.ArrayList;

public class Yapper {
    private static Scanner scanner = new Scanner(System.in);
    private static String name = "Yapper";
    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        printIntroduction();
        while(true) {
            String request = scanner.nextLine();
            if (request.equals("bye")) {
                break;
            } else if (request.equals("list")) {
                printList();
            }
            else {
                printHorizontalLine();
                list.add(request);
                System.out.println("\tadded: " + request);
            }
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

    public static void printList() {
        printHorizontalLine();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
    }
}
