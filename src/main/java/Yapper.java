package main.java;

import java.util.Scanner;

public class Yapper {
    private static Scanner scanner = new Scanner(System.in);
    private static String name = "Yapper";
    private static TaskList taskList;

    public static void main(String[] args) {
        // Try to open the file. If cannot, catch error and start a new chat.
        // Otherwise, use the tasks saved in the task list previously

        // Finally, also implement a feature that would save data of tasks added by user
        // everytime a new task is added (even if program crash can continue)
        taskList = DataStorage.loadData();
        printIntroduction();
        while (true) {
            taskList.activateToPrint();
            String request = scanner.nextLine().trim();
            String command = request.split(" ")[0];
            printHorizontalLine();
            if (command.equals("bye")) {
                break;
            }
            taskList = Parser.parseCommand(request, taskList);
            printHorizontalLine();
            DataStorage.saveData(taskList);
        }
        printExit();
        scanner.close();
        printHorizontalLine();
    }

    private static void printIntroduction() {
        printHorizontalLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        int length = 80;
        String horizontalLine = "-".repeat(length);
        System.out.println("\t" + horizontalLine);
    }

    private static void printExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
