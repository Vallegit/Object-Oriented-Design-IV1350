package integration;

/**
 * Represents an item.
 */
public class Item {
    int identifier;
    String description;
    int price;
    int vatRate; 

    /**
     * Creates a new instance of an item.
     * @param itemIdentifier - Number that corresponds to a certain item.
     */
    public Item(int itemIdentifier) {
        this.identifier = itemIdentifier;

        switch(identifier) {
            case 0:
                description = "banana";
                price = 1337;
                vatRate = 0;
                break;
            
            case 1:
                description = "toilet paper";
                price = 1;
                vatRate = 100;
                break;

            case 2:
                description = "hot dog";
                price = 2;
                vatRate = 99;
                break;

            case 3:
                description = "gold bar";
                price = 5;
                vatRate = 0;
                break;

            case 4:
                description = "snickers";
                price = 1000000;
                vatRate = 25;
                break;
        }
    }

    /**
     * Retrieves the identification number of an item.
     * 
     * @return itemIdentifier number of an item.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * Retrieves the price of an item.
     * 
     * @return Price of an item.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Retrieves the vatRate and calculates amount of vat to pay.
     * 
     * @return Amount of vat to pay.
     */
    public int getVat() {
        return this.vatRate * price / 100;
    }

    /**
     * Retrieves the item description
     * 
     * @return The description of an item.
     */
    public String getDescription() {
        return this.description;
    }
}
