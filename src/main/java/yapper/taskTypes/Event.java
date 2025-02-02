package yapper.taskTypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import yapper.exceptions.MissingTaskArgs;

public class Event extends Task {

     private LocalDate fromDate;
     private LocalTime fromTime;
     private LocalDate toDate;
     private LocalTime toTime;

     public Event(String request) {
         this.request = request;
         String[] splitString = request.split("event | /from | /to ");
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

         if (splitString.length < 3) {
            throw new MissingTaskArgs("\tHey! I dont quite understand you. Remember for Events " +
                    "Give it in this format: command name /from {YYYY/MM/DD} {0000} /to {YYYY/MM/DD} {2359}");
         }
         this.taskName = splitString[1].trim();
         this.fromDate = LocalDate.parse(splitString[2].trim(), formatter);
         this.fromTime = LocalTime.parse(splitString[2].trim(), formatter);
         this.toDate = LocalDate.parse(splitString[3].trim(), formatter);
         this.toTime = LocalTime.parse(splitString[3].trim(), formatter);
     }

     @Override
     public String toString() {
         return "[E]" + super.toString() + " (from: " + this.fromDate + " " + this.fromTime +
                 " to: " + this.toDate + " " + this.toTime + ")";
     }
 }