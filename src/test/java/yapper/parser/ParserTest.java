package yapper.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yapper.tasktypes.TaskList;

public class ParserTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testExecuteCommandValidTodo() {
        String input = "todo Buy milk";
        taskList = Parser.executeCommand(input, taskList);

        assertEquals(1, taskList.getList().size(), "TaskList should contain 1 task after valid 'todo' command");
        assertEquals("todo Buy milk", taskList.getList().get(0).getUserInput(), "Task description should match");
    }

    @Test
    void testExecuteCommandValidDeadline() {
        String input = "deadline Submit report /by 2024/02/05 0000";
        taskList = Parser.executeCommand(input, taskList);

        assertEquals(1, taskList.getList().size(), "TaskList should contain 1 task after valid 'deadline' command");
        assertEquals("deadline Submit report /by 2024/02/05 0000", taskList.getList().get(0).getUserInput(),
                "Deadline task should match input");
    }

    @Test
    void testExecuteCommandInvalidCommand() {
        String input = "randomtext xyz";
        taskList = Parser.executeCommand(input, taskList);

        assertEquals(0, taskList.getList().size(), "TaskList should remain empty for an invalid command");
    }

    @Test
    void testExecuteCommandEmptyInput() {
        String input = "";
        taskList = Parser.executeCommand(input, taskList);

        assertEquals(0, taskList.getList().size(), "TaskList should remain empty for an empty command");
    }
}
