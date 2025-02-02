package yapper;

import java.util.Scanner;
import yapper.parser.Parser;
import yapper.ui.UI;
import yapper.datastorage.DataStorage;
import yapper.taskTypes.TaskList;

public class Yapper {
    private final Scanner scanner;
    private final TaskList taskList;
    private final DataStorage dataStorage;
    private final String BOT_NAME = "Yapper";
    private final UI ui;

    public Yapper(String filePath) {
        dataStorage = new DataStorage(filePath);
        taskList = dataStorage.loadData();
        scanner = new Scanner(System.in);
        ui = new UI(BOT_NAME);
    }

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

    public static void main(String[] args) {
        new Yapper("../data/YapperTasks.txt").run();
    }
}