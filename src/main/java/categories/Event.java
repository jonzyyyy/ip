package main.java.categories;

import main.java.exceptions.MissingTaskArgs;

public class Event extends Task {

     private String fromDatetime;
     private String toDatetime;

     public Event(String request) {
         String[] splitString = request.split("event | /from | /to ");

         if (splitString.length < 3) {
            throw new MissingTaskArgs("\tHey! I dont quite understand you. Remember for Events " +
                    "Give it in this format: command name /from date /to date");
         }
         this.taskName = splitString[1].trim();
         this.fromDatetime = splitString[2].trim();
         this.toDatetime = splitString[3].trim();
     }

     @Override
     public String toString() {
         return "[E]" + super.toString() + " (from: " + fromDatetime + " to: " + toDatetime + ")";
     }
 }