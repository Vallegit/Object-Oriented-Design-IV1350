package integration;
import model.Sale;

/**
 * Represents a printer
 */
public class Printer {
    
    /**
     * Creates a new instance of the Printer class
     */
    public Printer(){

    }

    /**
     * Prints that the printer is working to represent a real printer
     * @param sale - sale to be printed (if this was real)
     */
    public void print(Sale sale) {
        System.out.println("Printing Receipt...");
    }
}
