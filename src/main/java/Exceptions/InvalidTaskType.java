package main.java.Exceptions;

public class InvalidTaskType extends RuntimeException {
    public InvalidTaskType(String message) {
        super(message);
    }
}