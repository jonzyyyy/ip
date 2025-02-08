package yapper.tasktypes;

import java.util.ArrayList;


/**
 * Represents a list of tasks that can be manipulated by adding, removing,
 * marking, and unmarking tasks. Supports optional printing of operations.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private boolean isToPrint = false;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Activates printing of messages when tasks are added, removed, or modified.
     */
    public void activateToPrint() {
        this.isToPrint = true;
    }

    /**
     * Deactivates printing of messages when tasks are added, removed, or modified.
     */
    public void deactivateToPrint() {
        this.isToPrint = false;
    }

    /**
     * Adds a task to the task list.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param newTask The task to be added.
     */
    public String addTask(Task newTask) {
        String str = "";
        this.tasks.add(newTask);
        if (this.isToPrint) {
            str = ("Got it. I've added this task:\n\t" + newTask
                + "\nNow you have " + this.tasks.size() + " tasks in the list.");
        }
        return str;
    }

    /**
     * Deletes a task from the task list based on its index.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param strIndex The index of the task to be removed (1-based).
     */
    public String deleteTask(String strIndex) {
        String str = "";
        int index;
        try {
            index = Integer.parseInt(strIndex) - 1;
            if (index < 0 || index >= this.tasks.size()) {
                str = "Error: Invalid index. Please enter a number between 1 and " + this.tasks.size();
            }
            Task task = this.tasks.remove(index);
            if (this.isToPrint) {
                str = "Noted. I've removed this task:\n\t" + task
                    + "Now you have " + this.tasks.size() + " tasks in the list.";
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid index to remove task according to the list.";
        }
        return str;
    }

    /**
     * Finds respective tasks from the task list based on string matching.
     *
     * @param keyword The keyword(s) that may be found in a task name
     */
    public String findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        boolean hasFoundTask = false;
        for (Task task : tasks) {
            if (task.taskName.contains(keyword)) {
                if (!hasFoundTask) {
                    sb.append("Here are the matching tasks in your list:");
                    hasFoundTask = true;
                }
                sb.append("\n\t" + index + "." + task);
                index++;
            }
        }
        if (!hasFoundTask) {
            sb.append("There are no matching tasks in your list containing: " + keyword);
        }
        return sb.toString();
    }

    private String toggleTaskCompletion(int index, boolean completed) {
        String str = "";
        if (index < 0 || index >= tasks.size()) {
            str = "Please enter a valid index to remove task according to the list.";
        }
        Task task = tasks.get(index);
        if (completed) {
            task.setCompleted();
            if (isToPrint) {
                str = "Nice! I've marked this task as done:";
            }
        } else {
            task.setNotCompleted();
            if (isToPrint) {
                str = "OK, I've marked this task as not done yet:";
            }
        }
        if (isToPrint) {
            str += "\n\t" + task;
        }
        return str;
    }


    /**
     * Marks a task as completed.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param index The zero-based index of the task to be marked as done.
     */
    public String markItem(int index) {
        return toggleTaskCompletion(index, true);
    }

    /**
     * Unmarks a completed task, setting it back to not completed.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param index The zero-based index of the task to be unmarked.
     */
    public String unmarkItem(int index) {
        return toggleTaskCompletion(index, false);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns a string representation of the task list, displaying all tasks in order.
     *
     * @return A formatted string representing the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n\t").append(i + 1).append(".").append(tasks.get(i));
        }
        return sb.toString();
    }
}
