package yapper.taskTypes;

import yapper.exceptions.MissingTaskArgs;

public class ToDo extends Task {

    public ToDo(String request) {
        this.request = request;
        String[] splitString = request.split(" ", 2);
        if (splitString.length < 2) {
            throw new MissingTaskArgs("\tHey! Dont just tell me the type of command, tell me " +
                "what your task is. And leave a space between words will ya.");
        }
        this.taskName = splitString[1];
    }

    @Override
    public String toString() {
       return "[T]" + super.toString();
    }
}