package yapper.taskTypes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a list of tasks that can be manipulated by adding, removing,
 * marking, and unmarking tasks. Supports optional printing of operations.
 */
public class TaskList {
    private ArrayList<Task> list;
    private boolean toPrint = false;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Activates printing of messages when tasks are added, removed, or modified.
     */
    public void activateToPrint() {
        this.toPrint = true;
    }

    /**
     * Deactivates printing of messages when tasks are added, removed, or modified.
     */
    public void deactivateToPrint() {
        this.toPrint = false;
    }

    /**
     * Reverses the order of tasks in the list.
     */
    public void reverseList() {
        Collections.reverse(this.list);
    }

    /**
     * Adds a task to the task list.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        this.list.add(newTask);
        if (this.toPrint) {
            System.out.println("\tGot it. I've added this task:\n" + "\t\t" + newTask);
            System.out.println("\tNow you have " + this.list.size() + " tasks in the list.");
        }
    }

    /**
     * Deletes a task from the task list based on its index.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param strIndex The index of the task to be removed (1-based).
     */
    public void deleteTask(String strIndex) {
        int index = Integer.parseInt(strIndex) - 1;
        Task task = this.list.get(index);
        this.list.remove(index);
        if (this.toPrint) {
            System.out.println("\tNoted. I've removed this task:" +
                    "\n\t\t" + task + "\n\tNow you have " + this.list.size() + " tasks in the list.");
        }
    }

    /**
     * Marks a task as completed.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param index The zero-based index of the task to be marked as done.
     */
    public void markItem(int index) {
        Task task = this.list.get(index);
        task.setCompleted();
        if (this.toPrint) {
            System.out.println("\t" + "Nice! I've marked this task as done:");
            System.out.println("\t\t" + task);
        }
    }

    /**
     * Unmarks a completed task, setting it back to not completed.
     * If printing is enabled, a confirmation message is displayed.
     *
     * @param index The zero-based index of the task to be unmarked.
     */
    public void unmarkItem(int index) {
        Task task = this.list.get(index);
        task.setNotCompleted();
        if (this.toPrint) {
            System.out.println("\t" + "OK, I've marked this task as not done yet:");
            System.out.println("\t\t" + task);
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns a string representation of the task list, displaying all tasks in order.
     *
     * @return A formatted string representing the list of tasks.
     */
    @Override
    public String toString() {
        String str = "\tHere are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            str += "\n\t" + (i + 1) + "." + list.get(i);
        }
        return str;
    }
}