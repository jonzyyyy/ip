package yapper.taskTypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import yapper.exceptions.MissingTaskArgs;

public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    public Deadline(String request) {
        this.request = request;
        String[] splitString = request.split("deadline |/by ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

        if (splitString.length < 3) {
            throw new MissingTaskArgs("\tHey! I dont quite understand you. Remember for Deadline " +
                    "Give it in this format: command name /by date");
        }

        this.taskName = splitString[1].trim();
        this.date = LocalDate.parse(splitString[2].trim(), formatter);
        this.time = LocalTime.parse(splitString[2].trim(), formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}
