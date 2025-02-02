package yapper.exceptions;

public class MissingTaskArgs extends RuntimeException {
    public MissingTaskArgs(String message) {
        super(message);
    }
}