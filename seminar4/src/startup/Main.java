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

        view.run();
    }
}

