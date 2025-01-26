package main.java;

import java.util.Scanner;
import main.java.Exceptions.*;
import java.util.ArrayList;

public class Yapper {
    private static Scanner scanner = new Scanner(System.in);
    private static String name = "Yapper";
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        printIntroduction();
        while(true) {
            String request = scanner.nextLine().trim();
            String[] splitRequest = request.split(" ");
            String command = splitRequest[0];
            printHorizontalLine();

            try {
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    printList();
                } else if (command.equals("todo")) {
                    Task newTask = new ToDo(request);
                    addTask(newTask);
                } else if (command.equals("deadline")) {
                    Task newTask = new Deadline(request);
                    addTask(newTask);
                } else if (command.equals("event")) {
                    Task newTask = new Event(request);
                    addTask(newTask);
                } else if (command.equals("mark")) {
                    // Checks for mark commanda
                    int index = splitRequest[1].charAt(0) - '0' - 1;
                    markItem(index);
                } else if (command.equals("unmark")) {
                    // Checks for unmark command
                    int index = splitRequest[1].charAt(0) - '0' - 1;
                    unmarkItem(index);
                } else {
                    System.out.println("Come on we've been through this. I can " +
                        "only understand these 3 commands: 'todo', 'deadline', 'event'" +
                        "Please give it in this format {Command taskname}");
                }
            } catch (MissingTaskArgs e) {
                System.out.println(e.getMessage());
            }
            printHorizontalLine();
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
        int length = 50;
        String horizontalLine = "-".repeat(length);
        System.out.println("\t" + horizontalLine);
    }

    private static void printExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    private static void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
        }
    }

    private static void markItem(int index) {
        Task task = list.get(index);
        task.setCompleted();
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    private static void unmarkItem(int index) {
        Task task = list.get(index);
        task.setNotCompleted();
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println("\t\t" + task);
    }


    private static void addTask(Task newTask) {
        list.add(newTask);
        System.out.println("\tGot it. I've added this task:\n" + "\t\t" + newTask);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }
}
