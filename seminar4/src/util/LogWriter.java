package util;
/**
 * Represents a logWriter
 */
public class LogWriter {
    /**
     * A logWriter logging function that logs the errors that was caught during runtime.
     */
   public static void log(Exception e) {
        System.out.println(
            "\n--------\nBEGIN LOG\n"
            + "LOG ENTRY | "
            + e.getMessage()
            + " | "
            + java.time.LocalDateTime.now()
            + "\nEND LOG\n--------\n");
    } 
}
