public class Deadline extends Task {

    private String datetime;

    public Deadline(String request) {
        super(request.split("deadline |/by ")[1].trim());
        String[] splitString = request.split("deadline |/by ");
        this.datetime = splitString[2].trim();
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + datetime + ")";
    }
}
