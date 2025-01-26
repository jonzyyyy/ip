package main.java.Exceptions;

public class MissingTaskArgs extends RuntimeException {
    public MissingTaskArgs(String message) {
        super(message);
    }
}