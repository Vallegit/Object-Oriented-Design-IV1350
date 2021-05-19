package integration;

/**
 * Represents a customer databas
 */
public class CustomerDB {

    /**
     * Creates a new instance of the CustomerDB class
     */
    public CustomerDB() {

    }

    /**
     * Checks if a customer is eligable for a discount
     * @param customerId - id of the customer
     * @return - true if customer is eligable, false otherwise
     */
    public Boolean checkDiscount(int customerId) {
        if(customerId > 69 && customerId < 420)
            return true;
        else 
            return false;
    }
}
