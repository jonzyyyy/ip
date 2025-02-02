package yapper;

import java.util.Scanner;
import yapper.parser.Parser;
import yapper.ui.UI;
import yapper.datastorage.DataStorage;
import yapper.taskTypes.TaskList;

public class Yapper {
    private Scanner scanner;
    private TaskList taskList;
    private DataStorage dataStorage;
    private final String BOT_NAME = "Yapper";
    private UI ui;

    public Yapper(String filePath) {
        this.dataStorage = new DataStorage(filePath);
        this.taskList = this.dataStorage.loadData();
        this.scanner = new Scanner(System.in);
        this.ui = new UI(BOT_NAME);
    }

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

    public static void main(String[] args) {
        new Yapper("../data/YapperTasks.txt").run();
    }
}
