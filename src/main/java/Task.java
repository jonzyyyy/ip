public abstract class Task {

    private boolean completed = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        if (completed) {
           return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }

    public void setCompleted() {
        completed = true;
    }

    public void setNotCompleted() {
        completed = false;
    }

}