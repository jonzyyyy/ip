public class ToDo extends Task {

    public ToDo(String request) {
        super(request.substring(5).trim());
    }

    @Override
    public String toString() {
       return "[T]" + super.toString();
    }
}