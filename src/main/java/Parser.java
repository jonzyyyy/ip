package main.java;

import main.java.exceptions.InvalidTaskType;
import main.java.exceptions.MissingTaskArgs;
import main.java.categories.Event;
import main.java.categories.Deadline;
import main.java.categories.ToDo;
import main.java.categories.Task;


public class Parser {

    private Parser() {}

    public static String parseCommand(String request) {
        return request.split(" ")[0];
    }

    public static TaskList executeCommand (String request, TaskList taskList) {
        String[] splitRequest = request.split(" ");
        String command = splitRequest[0];
        try {
            if (command.equals("list")) {
                System.out.println(taskList);
            } else if (command.equals("todo")) {
                Task newTask = new ToDo(request);
                taskList.addTask(newTask);
            } else if (command.equals("deadline")) {
                Task newTask = new Deadline(request);
                taskList.addTask(newTask);
            } else if (command.equals("event")) {
                Task newTask = new Event(request);
                taskList.addTask(newTask);
            } else if (command.equals("mark")) {
                // Checks for mark commanda
                int index = splitRequest[1].charAt(0) - '0' - 1;
                taskList.markItem(index);
            } else if (command.equals("unmark")) {
                // Checks for unmark command
                int index = splitRequest[1].charAt(0) - '0' - 1;
                taskList.unmarkItem(index);
            } else if (command.equals("delete")) {
                taskList.deleteTask(splitRequest[1]);
            } else {
                System.out.println("\tCome on we've been through this.\n" +
                    "\tonly understand these 3 commands: 'todo', 'deadline', 'event'.\n" +
                    "\tPlease give it in this format {Command taskname}");
            }
        } catch (MissingTaskArgs e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid index to remove task according to the list.");
        }
        return taskList;
    }
}