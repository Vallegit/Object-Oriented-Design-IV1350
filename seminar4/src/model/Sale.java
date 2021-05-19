package model;
import integration.CustomerDB;
import integration.Item;
import integration.Printer;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a sale
 */
public class Sale {
    private class ItemQuantity{
        Item item;
        int quantity;
        public ItemQuantity(Item item, int quantity){
            this.item = item;
            this.quantity = quantity;
        }
    }
    
    private int totalPrice = 0;
    private List<ItemQuantity> itemList;
    private CustomerDB customerDB;
    private Printer printer;
    private List<RevenueObserver> revenueObservers;

    /**
     * Creates a new instance of the Sale class
     * @param customerDB - reference to the customer database
     * @param printer - reference to the printer
     */
    public Sale(CustomerDB customerDB, Printer printer){
        this.customerDB = customerDB;
        this.printer = printer;
        itemList = new ArrayList<ItemQuantity>();
        revenueObservers = new ArrayList<RevenueObserver>();
    }

    /**
     * Updates the sale object with a new quantity of a specific item
     * and updates the total price of the sale accordingly
     * @param item - item to be added
     * @param quantity - quantity of items to be added
     */
    public void update(Item item, int quantity){
        if(searchItem(item) > -1)
            itemList.get(searchItem(item)).quantity += quantity;
        else 
            itemList.add(new ItemQuantity(item, quantity));
        totalPrice += (item.getPrice() + item.getVat()) * quantity;
    }

    private int searchItem(Item item){
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).item.getIdentifier() == item.getIdentifier()){
                return i;
            }
        }
        return -1;
    }

    /**
     * Ends the current sale
     * @return - total price of the sale
     */
    public int endSale(){
        return totalPrice;
    }

    /**
     * Prints the receit, completes sale and notifies observers.
     * @param amount - amount received from customer
     * @return - amount of change to return to customer
     */
    public int payment(int amount){
        Receipt receipt = new Receipt(this, printer);
        receipt.print();
        notifyObservers();
        return amount-totalPrice;
    }

    /**
     * Checks if customer is eligable for discount and applies 
     * the discount to the total price if the customer is eligable
     * @param customerId - id of customer
     * @return - total price after discount is applied
     */
    public int applyDiscount(int customerId){
        if(customerDB.checkDiscount(customerId)){
            return totalPrice = totalPrice/2;
        }
        else {
            return totalPrice;
        }
    }
    
    /**
     * Prints out the current sale information containing
     * current items, quantity of current items and
     * total price of all items.
     * 
     * @return Text information about the current sale.
     */
    public String toString() {
        String printOut = "\n----- SALE INFO -----\n";
        for(int i = 0; i < itemList.size(); i++){
            printOut += itemList.get(i).item.getDescription() + ": " + itemList.get(i).quantity + "x \n";
        }

        printOut += "Total Price: " + totalPrice + "\n" + "----- SALE INFO -----\n\n";
        return printOut;
    }

    private void notifyObservers() {
        for(RevenueObserver obs: revenueObservers) {
            obs.completedSale(totalPrice);
        }
    }

    /**
     * Adds a list of observers to the revenueObserves list.
     * @param observers - the list of observers to add.
     */
    public void addRevenueObservers(List<RevenueObserver> observers) {
        revenueObservers.addAll(observers);
    }
}
