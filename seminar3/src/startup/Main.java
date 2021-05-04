package startup;
import controller.Controller;
import view.View;
import integration.*;

public class Main {

    /**
     * Runs an instance of the program.
     */
    public static void main(String[] args) {
        Printer printer = new Printer();
        EAS eas = new EAS();
        EIS eis = new EIS();
        CustomerDB customerDB = new CustomerDB();
        Controller controller = new Controller(printer, eas, eis, customerDB);
        View view = new View(controller);

        boolean running = true;

        while(running){
            view.printMenu();
            int input = view.nextInt();
            int id, quantity, totalPrice, change;

            switch(input) {
                case 1:
                    controller.initiateSale();
                    break;
                case 2:
                    id = view.nextInt();
                    controller.addItem(id);
                    view.printSale();
                    break;
                case 3:
                    id = view.nextInt();
                    quantity = view.nextInt();
                    controller.addItem(id, quantity);
                    view.printSale();
                    break;
                case 4:
                    totalPrice = controller.endSale();
                    view.printTotalPrice(totalPrice);
                    break;
                case 5:
                    id = view.nextInt();
                    totalPrice = controller.discountRequest(id);
                    view.printTotalPrice(totalPrice);
                    break;
                case 6: 
                    quantity = view.nextInt();
                    change = controller.payment(quantity);
                    view.printChange(change);
                    break;
                case 7:
                    running = false;
                    break;
            }
        }
    }
}

