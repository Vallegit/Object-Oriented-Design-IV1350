package integration;
import model.Sale;

/**
 * Represent an external accounting system.
 */
public class EAS {

    /**
     * Creates a new instance of the EAS class.
     *
     */
    public EAS() {

    }

    /**
     * A print-out method that represents an EAS completed sale procedure 
     * confirmation message.
     * 
     * @param Sale - Sale to be completed
     */
    public void completedSale(Sale sale) {
        System.out.println("EAS completing sale");
    }
}
