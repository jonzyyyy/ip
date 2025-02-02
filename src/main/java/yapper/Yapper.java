package yapper;

import java.util.Scanner;
import yapper.parser.Parser;
import yapper.ui.UI;
import yapper.datastorage.DataStorage;
import yapper.taskTypes.TaskList;

/**
 * The main class for the Yapper chatbot, which manages task storage, user interactions,
 * and command execution.
 */
public class Yapper {
    private final Scanner scanner;
    private final TaskList taskList;
    private final DataStorage dataStorage;
    private final String BOT_NAME = "Yapper";
    private final UI ui;

    /**
     * Constructs a {@code Yapper} chatbot instance.
     * Initializes data storage, loads existing tasks, and sets up user interaction.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public Yapper(String filePath) {
        dataStorage = new DataStorage(filePath);
        taskList = dataStorage.loadData();
        scanner = new Scanner(System.in);
        ui = new UI(BOT_NAME);
    }

    /**
     * Runs the chatbot, handling user input, executing commands, and managing task persistence.
     * The chatbot continues running until the user enters the "bye" command.
     */
    public void run() {
        ui.printIntroduction();
        while (true) {
            taskList.activateToPrint();
            String request = scanner.nextLine().trim();
            ui.printHorizontalLine();
            String command = Parser.parseCommand(request);
            if (command.equals("bye")) {
                break;
            }
            Parser.executeCommand(request, taskList); // Modify `taskList` directly
            ui.printHorizontalLine();
            dataStorage.saveData(taskList);
        }
        ui.printExit();
        scanner.close(); // Ensure scanner is properly closed
        ui.printHorizontalLine();
    }

    /**
     * The entry point of the Yapper chatbot application.
     * Initializes and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Yapper("../data/YapperTasks.txt").run();
    }
}