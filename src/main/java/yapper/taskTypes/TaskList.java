package yapper.taskTypes;

import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> tasks;
    private boolean isToPrint = false;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void activateToPrint() {
        this.isToPrint = true;
    }

    public void reverseList() {
        Collections.reverse(this.tasks);
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        if (this.isToPrint) {
            System.out.println("\tGot it. I've added this task:\n\t\t" + newTask);
            System.out.println("\tNow you have " + this.tasks.size() + " tasks in the list.");
        }
    }

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

    public void markItem(int index) {
        toggleTaskCompletion(index, true);
    }

    public void unmarkItem(int index) {
        toggleTaskCompletion(index, false);
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n\t").append(i + 1).append(".").append(tasks.get(i));
        }
        return sb.toString();
    }
}