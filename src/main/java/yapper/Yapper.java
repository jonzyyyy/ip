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
    private Scanner scanner;
    private TaskList taskList;
    private DataStorage dataStorage;
    private final String BOT_NAME = "Yapper";
    private UI ui;

    /**
     * Constructs a {@code Yapper} chatbot instance.
     * Initializes data storage, loads existing tasks, and sets up user interaction.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public Yapper(String filePath) {
        this.dataStorage = new DataStorage(filePath);
        this.taskList = this.dataStorage.loadData();
        this.scanner = new Scanner(System.in);
        this.ui = new UI(BOT_NAME);
    }

    /**
     * Runs the chatbot, handling user input, executing commands, and managing task persistence.
     * The chatbot continues running until the user enters the "bye" command.
     */
    public void run() {
        this.ui.printIntroduction();
        while (true) {
            this.taskList.activateToPrint();
            String request = scanner.nextLine().trim();
            this.ui.printHorizontalLine();
            String command = Parser.parseCommand(request);
            if (command.equals("bye")) {
                break;
            }
            this.taskList = Parser.executeCommand(request, this.taskList);
            this.ui.printHorizontalLine();
            this.dataStorage.saveData(taskList);
        }
        this.ui.printExit();
        scanner.close();
        this.ui.printHorizontalLine();
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