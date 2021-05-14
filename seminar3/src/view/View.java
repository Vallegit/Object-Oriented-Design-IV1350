package view;
import controller.Controller;
import java.util.Scanner;

/**
 * Represents the view of the program.
 */
public class View {
    Controller controller;
    Scanner scanner;

    /**
     * Constructs a View object
     * @param controller
     */
    public View(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a multiple choice menu representing the options in the program.
     */
    public void printMenu() {
        System.out.print(
            "\n1. Initiate Sale\n" +
            "2. Add item\n" +
            "3. Add item with quantity\n" +
            "4. End Sale\n" +
            "5. Discount Request\n" +
            "6. Payment\n" + 
            "7. Shutdown\n\n"
        );
    }

    /**
     * Reads an int from the standard input channel.
     * @return The inputed int
     */
    public int nextInt() {
        return scanner.nextInt();
    }

    /**
     * Prints a sale instance.
     */
    public void printSale() {
        System.out.print(controller.saleToString());
    }

    /**
     * Prints the total price of the current sale
     * @param totalPrice - The total price of the current sale
     */
    public void printTotalPrice(int totalPrice) {
        System.out.print("\n----------\nTOTAL PRICE: " + totalPrice + "\n----------\n");
    }

    /**
     * Prints the change resulting from a payment in the current sale
     * @param change - The resulting change
     */
    public void printChange(int change) {
        System.out.print("\n----------\nCHANGE: " + change + "\n----------\n");
    }

    public void run() {
        boolean running = true;

        while(running){
            this.printMenu();
            int input = this.nextInt();
            int id, quantity, totalPrice, change;

            switch(input) {
                case 1:
                    controller.initiateSale();
                    break;
                case 2:
                    id = this.nextInt();
                    controller.addItem(id);
                    this.printSale();
                    break;
                case 3:
                    id = this.nextInt();
                    quantity = this.nextInt();
                    controller.addItem(id, quantity);
                    this.printSale();
                    break;
                case 4:
                    totalPrice = controller.endSale();
                    this.printTotalPrice(totalPrice);
                    break;
                case 5:
                    id = this.nextInt();
                    totalPrice = controller.discountRequest(id);
                    this.printTotalPrice(totalPrice);
                    break;
                case 6: 
                    quantity = this.nextInt();
                    change = controller.payment(quantity);
                    this.printChange(change);
                    break;
                case 7:
                    running = false;
                    break;
            }
        }
    }
}
