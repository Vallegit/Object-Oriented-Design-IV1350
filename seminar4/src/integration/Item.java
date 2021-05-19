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
     * @param identifier - Number that corresponds to a certain item.
     * @param description - String that represents item description.
     * @param price - Number that represents item price.
     * @param vatRate - Number representing the VAT rate of an item in percent, 0 - 100.
     */
    public Item(int identifier, String description, int price, int vatRate) {
        this.identifier = identifier;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
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
