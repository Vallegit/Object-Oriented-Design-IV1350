package util;

import model.RevenueObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Represents a Fileoutput observer.
 */
public class TotalRevenueFileOutput implements RevenueObserver {
    private static final String REVENUE_FILE_NAME = "total-revenue.txt";
    private int totalRevenue;
    private PrintWriter revenueFile;
    
    /**
     * Writes the total revenue to file.
     */
    public TotalRevenueFileOutput() {
        totalRevenue = 0;
        try {
            revenueFile = new PrintWriter(new FileWriter(REVENUE_FILE_NAME), true);
        } catch (IOException e) {
            System.out.println("Could not create revenue file");
            e.printStackTrace();
        }
    }

    /** 
     * Adds the price of a new sale to the total revenue and prints it to a file.
     * @param totalPrice - the price to be added.
     */
    public void completedSale(int totalPrice) {
        totalRevenue += totalPrice;
        printRevenue();
    }
    
    private void printRevenue() {
        revenueFile.println("Total Revenue: " + totalRevenue + " pesos");
    }
}
