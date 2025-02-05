package yapper.tasktypes;

import java.util.ArrayList;
import java.util.Collections;

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
     * Reverses the order of tasks in the list.
     */
    public void reverseList() {
        Collections.reverse(this.tasks);
    }

    /**
     * Adds a task to the task list.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        if (this.isToPrint) {
            System.out.println("\tGot it. I've added this task:\n\t\t" + newTask);
            System.out.println("\tNow you have " + this.tasks.size() + " tasks in the list.");
        }
    }

    /**
     * Deletes a task from the task list based on its index.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param strIndex The index of the task to be removed (1-based).
     */
    public void deleteTask(String strIndex) {
        int index;
        try {
            index = Integer.parseInt(strIndex) - 1;
            if (index < 0 || index >= this.tasks.size()) {
                System.out.println("\tError: Invalid index. Please enter a number between 1 and " + this.tasks.size());
                return;
            }
            Task task = this.tasks.remove(index);
            if (this.isToPrint) {
                System.out.println("\tNoted. I've removed this task:\n\t\t" + task);
                System.out.println("\tNow you have " + this.tasks.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid index to remove task according to the list.");
        }
    }

    /**
     * Finds respective tasks from the task list based on string matching.
     *
     * @param keyword The keyword(s) that may be found in a task name
     */
    public void findTask(String keyword) {
        int index = 1;
        boolean hasFoundTask = false;
        for (Task task : tasks) {
            if (task.taskName.contains(keyword)) {
                if (!hasFoundTask) {
                    System.out.println("\tHere are the matching tasks in your list:");
                    hasFoundTask = true;
                }
                System.out.println("\t" + index + "." + task);
                index++;
            }
        }
        if (!hasFoundTask) {
            System.out.println("\t" + "There are no matching tasks in your list containing: " + keyword);
        }
    }

    private void toggleTaskCompletion(int index, boolean completed) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("\tPlease enter a valid index to remove task according to the list.");
            return;
        }
        Task task = tasks.get(index);
        if (completed) {
            task.setCompleted();
            if (isToPrint) {
                System.out.println("\tNice! I've marked this task as done:");
            }
        } else {
            task.setNotCompleted();
            if (isToPrint) {
                System.out.println("\tOK, I've marked this task as not done yet:");
            }
        }
        if (isToPrint) {
            System.out.println("\t\t" + task);
        }
    }


    /**
     * Marks a task as completed.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param index The zero-based index of the task to be marked as done.
     */
    public void markItem(int index) {
        toggleTaskCompletion(index, true);
    }

    /**
     * Unmarks a completed task, setting it back to not completed.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param index The zero-based index of the task to be unmarked.
     */
    public void unmarkItem(int index) {
        toggleTaskCompletion(index, false);
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
