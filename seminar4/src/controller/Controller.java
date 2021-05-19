package controller;
import integration.*;
import model.Sale;
import model.RevenueObserver;
import util.LogWriter;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the controller of the program
 */
public class Controller {

    private Printer printer;
    private EAS eas;
    private EIS eis;
    private CustomerDB customerDB;
    private Sale sale;
    private List<RevenueObserver> revenueObservers;

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
        revenueObservers = new ArrayList<RevenueObserver>();
    }

    /**
     * Initiates a new sale.
     */
    public void initiateSale() {
        sale = new Sale(customerDB, printer);
        sale.addRevenueObservers(revenueObservers);
    }

    /**
     * Adds a specified quantity of items to the current sale if the item
     * identifier is valid
     * @param itemIdentifier - id number of the item
     * @param quantity - quantity of the item to be added
     * @throws ItemNotFoundException - if item wasn't found in EIS
     * @throws Exception - if internal error occurs
     */
    public void addItem(int itemIdentifier, int quantity) throws ItemNotFoundException, Exception {
        try{
            Item item = eis.getItem(itemIdentifier);
            sale.update(item, quantity);
        } catch(ItemNotFoundException e) {
            throw e;
        } catch(Exception e) {
            LogWriter.log(e);
            throw new Exception("Internal error");
        }
    }

    /**
     * Adds an item to the current sale if the item identifier is valid
     * @param itemIdentifier - id number of the item 
     * @throws ItemNotFoundException - if item wasn't found in EIS
     * @throws Exception - if internal error occurs
     */
    public void addItem(int itemIdentifier) throws ItemNotFoundException, Exception {
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

    /**
     * Adds observers to a list.
     * @param RevenueObserver - a total revenue observer.
     */
    public void addRevenueObserver(RevenueObserver obs) {
        revenueObservers.add(obs);
    }
}
