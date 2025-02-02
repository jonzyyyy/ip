package yapper.parser;

import yapper.exceptions.MissingTaskArgs;
import yapper.taskTypes.Event;
import yapper.taskTypes.Deadline;
import yapper.taskTypes.ToDo;
import yapper.taskTypes.Task;
import yapper.taskTypes.TaskList;

public class Parser {

    private Parser() {}

    public static String parseCommand(String request) {
        return request.split(" ")[0];
    }

    public static TaskList executeCommand (String request, TaskList taskList) {
        String[] splitRequest = request.split(" ");
        String command = splitRequest[0];
        try {
            switch (command) {
            case "list" -> {
                System.out.println(taskList);
            }
            case "todo" -> {
                Task newTask = new ToDo(request);
                taskList.addTask(newTask);
            }
            case "deadline" -> {
                Task newTask = new Deadline(request);
                taskList.addTask(newTask);
            }
            case "event" -> {
                Task newTask = new Event(request);
                taskList.addTask(newTask);
            }
            case "mark" -> {
                int index = splitRequest[1].charAt(0) - '0' - 1;
                taskList.markItem(index);
            }
            case "unmark" -> {
                int index = splitRequest[1].charAt(0) - '0' - 1;
                taskList.unmarkItem(index);
            }
            case "delete" -> {
                taskList.deleteTask(splitRequest[1]);
            }
            default -> {
                System.out.println("\tCome on we've been through this.\n" +
                    "\tonly understand these 3 commands: 'todo', 'deadline', 'event'.\n" +
                    "\tPlease give it in this format {Command taskname}");
            }
            }
        } catch (MissingTaskArgs e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid index to remove task according to the list.");
        }
        return taskList;
    }
}