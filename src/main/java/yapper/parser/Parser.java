package yapper.parser;

import yapper.exceptions.MissingTaskArgs;

import yapper.taskTypes.Event;
import yapper.taskTypes.Deadline;
import yapper.taskTypes.ToDo;
import yapper.taskTypes.Task;
import yapper.taskTypes.TaskList;

/**
 * The {@code Parser} class is responsible for interpreting user commands
 * and executing the corresponding actions on the task list.
 */
public class Parser {

    /**
     * Private constructor to prevent instantiation of the {@code Parser} class.
     */
    private Parser() {}

    /**
     *
     * @param request The user input string containing command and relevant task details.
     * @return The command word extracted from the user input.
     */
    public static String parseCommand(String request) {
        return request.split(" ")[0];
    }

    /**
     *
     * Executes the user input request on the provided {@code TaskList}.
     *
     * Supported commands:
     * <ul>
     *   <li>{@code list} - Prints the task list.</li>
     *   <li>{@code todo} - Adds a new ToDo task.</li>
     *   <li>{@code deadline} - Adds a new Deadline task.</li>
     *   <li>{@code event} - Adds a new Event task.</li>
     *   <li>{@code mark} - Marks a task as completed.</li>
     *   <li>{@code unmark} - Unmarks a completed task.</li>
     *   <li>{@code delete} - Deletes a task from the list.</li>
     * </ul>
     * If an unsupported command is entered, an error message is displayed.
     *
     * @param request The user input command string.
     * @param taskList The current list of tasks.
     * @return The updated task list after executing the command.
     */
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
            case "find" -> {
                // Splits the request into "find" "Keyword / keywords"
                String keyword = request.split(" ", 2)[1];
                taskList.findTask(keyword);
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