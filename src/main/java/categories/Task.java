/**
 * This class is the superclass the models all Tasks within Yapper
 */
package main.java.categories;

public abstract class Task {

    private boolean completed = false;
    protected String taskName;
    protected String request;

    @Override
    public String toString() {
        if (completed) {
           return "[X] " + this.taskName;
        }
        return "[ ] " + taskName;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setNotCompleted() {
        this.completed = false;
    }

    public String getUserInput() {
        return this.request;
    }
}