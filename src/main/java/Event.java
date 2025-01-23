public class Event extends Task {

     private String fromDatetime;
     private String toDatetime;

     public Event(String request) {
         super(request.split("event | /from | /to ")[1].trim());
         String[] splitString = request.split("/from |/to ");
         this.fromDatetime = splitString[1].trim();
         this.toDatetime = splitString[2].trim();
     }

     @Override
     public String toString() {
         return "[E]" + super.toString() + " (from: " + fromDatetime + " to: " + toDatetime + ")";
     }
 }