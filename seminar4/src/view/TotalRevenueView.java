package view;
import model.RevenueObserver;

/**
 * Represents a totalrevenue observer
 */
public class TotalRevenueView implements RevenueObserver {
    private int totalRevenue;
    
    /**
     * Creates a new TotalRevenueView
     */
    public TotalRevenueView() {
        totalRevenue = 0;
    }
    
    /**
     * Adds the total price of a sale to the total revenue and prints to the console.
     * @param totalPrice - the price to be added.
     */
    public void completedSale(int totalPrice) {
        totalRevenue += totalPrice;
        printRevenue();
    }

    private void printRevenue() {
        System.out.println("Total Revenue: " + totalRevenue + " pesos");
    }
}
