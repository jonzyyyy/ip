import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;

public class Yapper {
    private static Scanner scanner = new Scanner(System.in);
    private static String name = "Yapper";
    private static ArrayList<String> list = new ArrayList<>();
    private static Hashtable<Integer, Boolean> markedItems = new Hashtable<>();

    public static void main(String[] args) {
        printIntroduction();
        while(true) {
            String request = scanner.nextLine();
            if (request.equals("bye")) {
                break;
            } else if (request.equals("list")) {
                printList();
            } else if (request.length() == 6 && request.substring(0, 5).equals("mark ") && Character.isDigit(request.charAt(5))) {
                // Checks if word contains mark X, where X is an integer
                int index = request.charAt(5) - '0' - 1;
                markItem(index);
            } else if (request.length() == 8 && request.substring(0, 7).equals("unmark ") && Character.isDigit(request.charAt(7))) {
                int index = request.charAt(7) - '0' - 1;
                unmarkItem(index);
            }
            else {
                printHorizontalLine();
                list.add(request);
                markedItems.put(list.size() - 1, false);
                System.out.println("\tadded: " + request);
            }
            printHorizontalLine();
        }
        printExit();
        scanner.close();
    }

    private static void printIntroduction() {
        printHorizontalLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        int length = 50;
        String horizontalLine = "-".repeat(length);
        System.out.println("\t" + horizontalLine);
    }

    private static void printExit() {
        printHorizontalLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printList() {
        printHorizontalLine();
        System.out.println("\tHere are tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (markedItems.get(i)) {
                System.out.println("\t" + (i + 1) + ".[X] " + list.get(i));
            } else {
                System.out.println("\t" + (i + 1) + ".[ ] " + list.get(i));
            }
        }
    }

    private static void markItem(int index) {
        System.out.println("\t" + "Nice! I've marked this task as done:");
        markedItems.put(index, true);
        System.out.println("\t\t[X] " + list.get(index));
    }

    private static void unmarkItem(int index) {
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        markedItems.put(index, false);
        System.out.println("\t\t[ ] " + list.get(index));
    }
}
