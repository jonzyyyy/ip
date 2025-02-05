package yapper.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UiTest {
    private UI ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Create the UI object with a placeholder name
        ui = new UI("Yapper");

        // Redirect standard output to capture it for comparison
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testPrintIntroduction() {
        ui.printIntroduction();
        // Capture the printed output
        String actualOutput = outputStream.toString();

        // Expected output (no extra newline at the end)
        String expectedOutput = """
                \t--------------------------------------------------------------------------------
                \tHello! I'm Yapper
                \tWhat can I do for you?
                \t--------------------------------------------------------------------------------
                """; // Added newline at the end

        // Compare expected vs actual
        assertEquals(expectedOutput, actualOutput,
                "Introduction message should match expected format."
        );
    }

    @Test
    void testPrintHorizontalLine() {
        ui.printHorizontalLine();
        String actualOutput = outputStream.toString(); // remove trailing newline
        String expectedOutput = "\t" + "-".repeat(80) + "\n";

        assertEquals(expectedOutput, actualOutput, "Horizontal line should be 80 dashes, preceded by a tab.");
    }

    @Test
    void testPrintExit() {
        ui.printExit();
        String actualOutput = outputStream.toString();
        String expectedOutput = "\tBye. Hope to see you again soon!" + "\n";

        assertEquals(expectedOutput, actualOutput,
                "Exit message should match expected format."
        );
    }

    // Optionally, restore System.out after each test
    // (especially if you have more tests in the suite that need console output)
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
