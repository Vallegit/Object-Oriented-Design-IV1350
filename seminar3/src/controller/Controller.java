package controller;
import integration.*;
import model.Sale;

/**
 * Represents the controller of the program
 */
public class Controller {

    private Printer printer;
    private EAS eas;
    private EIS eis;
    private CustomerDB customerDB;
    private Sale sale;

    /**
     * Creates a new instance of the Controller class
     * @param printer - reference to printer
     * @param eas - reference to External Accounting System
     * @param eis - reference to External Inventory System
     * @param customerDB - reference to customer Database
     */
    public Controller(Printer printer, EAS eas, EIS eis, CustomerDB customerDB) {
        this.printer = printer;
        this.eas = eas;
        this.eis = eis;
        this.customerDB = customerDB;
    }

    /**
     * Initiates a new sale.
     */
    public void initiateSale() {
        this.sale = new Sale(customerDB, printer); 
    }

    /**
     * Adds a specified quantity of items to the current sale if the item
     * identifier is valid
     * @param itemIdentifier - id number of the item
     * @param quantity - quantity of the item to be added
     */
    public void addItem(int itemIdentifier, int quantity) {
        boolean isValid = eis.checkValid(itemIdentifier);
        
        if(isValid) {
            Item item = eis.getItem(itemIdentifier);
            sale.update(item, quantity);
        }
    }

    /**
     * Adds an item to the current sale if the item identifier is valid
     * @param itemIdentifier - id number of the item
     */
    public void addItem(int itemIdentifier) {
        addItem(itemIdentifier, 1);
    }

    /**
     * Ends a sale
     * @return the total price of the sale
     */
    public int endSale() {
        return sale.endSale();
    }

    /**
     * Sends a discount request to the current sale
     * @param customerId - id number of the customer requesting a discount
     * @return - price after the discount has been applied
     */
    public int discountRequest(int customerId) {
        return sale.applyDiscount(customerId);
    }

    /**
     * Enters a paid amount into current sale and informs the external systems
     * of a completed sale
     * @param amount - amount of money received from the customer
     * @return - amount of change to return to the customer
     */
    public int payment(int amount) {
        int changeAmount = sale.payment(amount);
        eis.completedSale(sale);
        eas.completedSale(sale);
        this.sale = null;
        return changeAmount;
    }

    /**
     * Converts the current sale object to a string
     * @return - string representing the current sale
     */
    public String saleToString() {
        return sale.toString();
    }
}
