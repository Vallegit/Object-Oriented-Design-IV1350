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
     * @throws ItemNotFoundException if not item with identifier itemIdfentifier exists
     * @throws DatabaseNotRespondingException if database is not responding
     */
    public Item getItem(int itemIdentifier) throws ItemNotFoundException, DatabaseNotRespondingException {
        switch(itemIdentifier) {
            case 0:
                return new Item(0, "banana", 1337, 0);
            case 1:
                return new Item(1, "toilet paper", 1, 100);
            case 2:
                return new Item(2, "hot dog", 2, 99);
            case 3:
                return new Item(3, "gold bar", 5, 0);
            case 4:
                return new Item(4, "snickers", 1000000, 25);
            case 42069:
                throw new DatabaseNotRespondingException("Database can not be reached");
            default:
                throw new ItemNotFoundException("The item with identifier " + itemIdentifier + " does not exist!");
        }
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
