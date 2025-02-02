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

/**
 * Handles loading and saving of tasks from a data file.
 */
public class DataStorage {
    private File dataFile;

    /**
     * Constructs a DataStorage instance with the given file path.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public DataStorage(String filePath) {
        this.dataFile = new File(filePath);
    }

    /**
     * Loads tasks from the data file.
     * If the file does not exist, it is created, and an empty task list is returned.
     *
     * @return A TaskList containing the tasks loaded from the file.
     */
    public TaskList loadData() {
        TaskList taskList = new TaskList();
        try {
            // Ensure the parent directory exists
            File parentDir = this.dataFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Create file if it doesnt exist
            if (!this.dataFile.exists()) {
                this.dataFile.createNewFile();
                return taskList;
            }

            // Reads the file
            Scanner scanner = new Scanner(this.dataFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    taskList = Parser.executeCommand(line, taskList);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not seem to exist. " +
                    "Perhaps you might have run out of memory?\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("An IO error occurred: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the current list of tasks to the data file.
     * If the parent directory does not exist, it is created before writing to the file.
     *
     * @param taskList The list of tasks to be saved to the file.
     */
    public void saveData(TaskList taskList) {
        try (FileWriter fileWriter = new FileWriter(this.dataFile)) {
            ArrayList<Task> tasks = taskList.getList();

            // Ensure the parent directory exists
            File parentDir = this.dataFile.getParentFile();
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