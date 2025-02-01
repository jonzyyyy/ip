package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import main.java.categories.Task;
import java.util.ArrayList;
import java.util.Collections;

public class DataStorage {
    private static final String FILE_PATH = "../data/YapperTasks.txt";
    private static File dataFile = new File(FILE_PATH);
    public DataStorage() {}

    public static TaskList loadData() {
        TaskList taskList = new TaskList();
        try {
            // Ensure the parent directory exists
            File parentDir = dataFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Create file if it doesnt exist
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                return taskList;
            }

            // Reads the file
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    taskList = Parser.parseCommand(line, taskList);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not seem to exist. " +
                    "Perhaps you might have run out of memory?\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("An IO error occurred: " + e.getMessage());
        }
//        taskList.reverseList();
        return taskList;
    }
    public static void saveData(TaskList taskList) {
        try (FileWriter fileWriter = new FileWriter(dataFile)) {
            ArrayList<Task> tasks = taskList.getList();

            // Ensure the parent directory exists
            File parentDir = dataFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Write to file
            for (Task task: tasks) {
                String userInput = task.getUserInput();
                fileWriter.write(userInput + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file beacuse of: " + e.getMessage());
        }
    }
}