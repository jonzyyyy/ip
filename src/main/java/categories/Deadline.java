package main.java.categories;

import main.java.exceptions.MissingTaskArgs;

public class Deadline extends Task {

    private String datetime;

    public Deadline(String request) {
        this.request = request;
        String[] splitString = request.split("deadline |/by ");

        if (splitString.length < 3) {
            throw new MissingTaskArgs("\tHey! I dont quite understand you. Remember for Deadline " +
                    "Give it in this format: command name /by date");
        }

        this.taskName = splitString[1].trim();
        this.datetime = splitString[2].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datetime + ")";
    }
}
