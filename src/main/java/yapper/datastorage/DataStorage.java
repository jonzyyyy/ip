package yapper.datastorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import yapper.taskTypes.Task;
import yapper.taskTypes.TaskList;
import yapper.parser.Parser;

public class DataStorage {
    private File file;

    public DataStorage(String filePath) {
        this.file = new File(filePath);
    }

    public TaskList loadData() {
        TaskList taskList = new TaskList();
        try {
            // Ensure the parent directory exists
            File parentDir = this.file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                System.out.println("Failed to create directory: " + parentDir.getAbsolutePath());
            }

            // Create file if it does not exist
            if (!this.file.exists() && !this.file.createNewFile()) {
                System.out.println("Error: Failed to create file.");
                return taskList;
            }

            // Read the file
            try (Scanner scanner = new Scanner(this.file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        taskList = Parser.executeCommand(line, taskList);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An IO error occurred: " + e.getMessage());
        }
        return taskList;
    }

    public void saveData(TaskList taskList) {
        try (FileWriter fileWriter = new FileWriter(this.file)) {
            ArrayList<Task> tasks = taskList.getList();

            // Ensure the parent directory exists
            File parentDir = this.file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                System.out.println("Failed to create directory: " + parentDir.getAbsolutePath());
            }

            // Write to file
            for (Task task : tasks) {
                fileWriter.write(task.getUserInput() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}