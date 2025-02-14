package yapper;

import java.util.Scanner;

import yapper.datastorage.DataStorage;
import yapper.parser.Parser;
import yapper.tasktypes.TaskList;
import yapper.ui.UI;


/**
 * The main class for the Yapper chatbot, which manages task storage, user interactions, and command execution.
 */
public class Yapper {
    private static String FILE_PATH = "../data/YapperTasks.txt";
    private TaskList taskList;
    private DataStorage dataStorage;
    private UI ui;

    /**
     * Constructs a {@code Yapper} chatbot instance. Initializes data storage, loads existing tasks, and sets up user
     * interaction.
     */
    public Yapper() {
        dataStorage = new DataStorage(FILE_PATH);
        taskList = dataStorage.loadData();
        taskList.activateToPrint();
        ui = new UI();
    }

    public String getIntroduction() {
        return ui.getIntroduction();
    }

    /**
     * Runs the chatbot, handling user input, executing commands, and managing task persistence. The chatbot
     * continues running until the user enters the "bye" command.
     */
    public String getResponse(String input) {
        String request = input.trim();
        String command = Parser.parseCommand(request);
        if (command.equals("bye")) {
            return ui.getExit();
        }
        // Modifies `taskList` directly
        String response = Parser.executeCommand(request, taskList);
        dataStorage.saveData(taskList);
        return response;
    }
}
