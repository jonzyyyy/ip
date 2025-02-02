package yapper.taskTypes;

public abstract class Task {

    private boolean isCompleted = false;
    protected String taskName;
    protected String request;

    @Override
    public String toString() {
        if (isCompleted) {
           return "[X] " + this.taskName;
        }
        return "[ ] " + taskName;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setNotCompleted() {
        this.isCompleted = false;
    }

    public String getUserInput() {
        return this.request;
    }
}