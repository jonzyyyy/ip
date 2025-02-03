package yapper.taskTypes;

import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> list;
    private boolean toPrint = false;

    public TaskList(){
        this.list =  new ArrayList<Task>();
    }

    public void activateToPrint() {
        this.toPrint = true;
    }

    public void reverseList() {
        Collections.reverse(this.list);
    }

    public void addTask(Task newTask) {
        this.list.add(newTask);
        if (this.toPrint) {
            System.out.println("\tGot it. I've added this task:\n" + "\t\t" + newTask);
            System.out.println("\tNow you have " + this.list.size() + " tasks in the list.");
        }
    }

    public void deleteTask(String strIndex) {
        int index = Integer.parseInt(strIndex) - 1;
        Task task = this.list.get(index);
        this.list.remove(index);
        if (this.toPrint) {
            System.out.println("\tNoted. I've removed this task:" +
                    "\n\t\t" + task + "\n\tNow you have " + this.list.size() + " tasks in the list.");
        }
    }

    public void findTask(String keyword) {
        int index = 1;
        boolean hasFoundTask = false;
        for (Task task : list) {
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

    public void markItem(int index) {
        Task task = this.list.get(index);
        task.setCompleted();
        if (this.toPrint) {
            System.out.println("\t" + "Nice! I've marked this task as done:");
            System.out.println("\t\t" + task);
        }
    }

    public void unmarkItem(int index) {
        Task task = this.list.get(index);
        task.setNotCompleted();
        if (this.toPrint) {
            System.out.println("\t" + "OK, I've marked this task as not done yet:");
            System.out.println("\t\t" + task);
        }
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        String str = "\tHere are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            str += "\n\t" + Integer.toString(i + 1) + "." + list.get(i);
        }
        return str;
    }
}