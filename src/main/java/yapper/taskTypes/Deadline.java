package yapper.taskTypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import yapper.exceptions.MissingTaskArgs;

/**
 * Represents a deadline task with a specific date and time.
 */
public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    /**
     * Constructs a {@code Deadline} task from the given user request.
     * The request must be formatted as: {@code deadline task_name /by yyyy/MM/dd HHmm}.
     *
     * @param request The user input containing the task name and deadline details.
     * @throws MissingTaskArgs If the required arguments are missing or incorrectly formatted.
     */
    public Deadline(String request) {
        this.request = request;
        String[] splitString = request.split("deadline |/by ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

        if (splitString.length < 3) {
            throw new MissingTaskArgs("\tHey! I don't quite understand you. Remember for Deadline " +
                    "Give it in this format: command name /by date");
        }

        this.taskName = splitString[1].trim();
        this.date = LocalDate.parse(splitString[2].trim(), formatter);
        this.time = LocalTime.parse(splitString[2].trim(), formatter);
    }

    /**
     * Returns a string representation of the deadline task, including the date and time.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}