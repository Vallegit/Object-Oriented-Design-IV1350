package model;
import integration.Printer;

/**
* Represents a receipt.
*/

public class Receipt {

    String text;
    Printer printer;

    /**
    * Creates a receipt.
    * @param sale - The corresponding sale for the receipt.
    * @param printer - reference to printer.
    */

    public Receipt(Sale sale, Printer printer) {
        this.printer = printer;
        text = sale.toString();
    }

    /**
    * printer prints receipt
    */

    public void print() {
        printer.print(text);
    }
}
