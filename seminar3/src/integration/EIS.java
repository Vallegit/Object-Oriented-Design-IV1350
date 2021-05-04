package integration;
import model.Sale;

/**
 * A class that represents the external inventory system.
 */
public class EIS {

    /**
     * Creates a new instance representing EIS
     */
    public EIS() {
        
    }

    /**
     * Retrieves an item based on the itemIdentifier
     * 
     * @param itemIdentifier - number that corresponds to a certain item
     * @return Item object cointaining the data of a certain item
     */
    public Item getItem(int itemIdentifier) {
        return new Item(itemIdentifier);
    }

    /**
     * Checks if the itemIdentifier is a valid number that represents 
     * an actual item that exist in the EIS.
     * 
     * @param itemIdentifier - number that corresponds to a certain item.
     * @return The boolean result of the check.
     */
    public Boolean checkValid(int itemIdentifier) {
        if(itemIdentifier < 5 && itemIdentifier >= 0)
            return true;
        else
            return false;
    }
    
    /**
     * A print-out method that represents an EIS completed sale procedure 
     * confirmation message.
     * 
     * @param Sale - Sale to be completed.
     */
    public void completedSale(Sale sale) {
        System.out.println("EIS completing sale");
    }
}
