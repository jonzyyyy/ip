package yapper.taskTypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import yapper.exceptions.MissingTaskArgs;

/**
 * Represents an event task with a specific start and end date/time.
 */
public class Event extends Task {

    private LocalDate fromDate;
    private LocalTime fromTime;
    private LocalDate toDate;
    private LocalTime toTime;

    /**
     * Constructs an {@code Event} task from the given user request.
     * The request must be formatted as: {@code event task_name /from YYYY/MM/DD HHmm /to YYYY/MM/DD HHmm}.
     *
     * @param request The user input containing the task name, start time, and end time.
     * @throws MissingTaskArgs If the required arguments are missing or incorrectly formatted.
     */
    public Event(String request) {
        this.request = request;
        String[] splitString = request.split("event | /from | /to ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

        if (splitString.length < 4) {
            throw new MissingTaskArgs("\tHey! I don't quite understand you. Remember for Events " +
                    "Give it in this format: command name /from {YYYY/MM/DD} {0000} /to {YYYY/MM/DD} {2359}");
        }

        this.taskName = splitString[1].trim();
        this.fromDate = LocalDate.parse(splitString[2].trim(), formatter);
        this.fromTime = LocalTime.parse(splitString[2].trim(), formatter);
        this.toDate = LocalDate.parse(splitString[3].trim(), formatter);
        this.toTime = LocalTime.parse(splitString[3].trim(), formatter);
    }

    /**
     * Returns a string representation of the event task, including start and end date/time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate + " " + this.fromTime +
                " to: " + this.toDate + " " + this.toTime + ")";
    }
}