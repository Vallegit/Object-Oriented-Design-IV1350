package tests;

import controller.Controller;
import integration.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.NullPointerException;

public class ControllerTest {

    public static void initiateSaleTest(Printer printer, EAS eas, EIS eis, CustomerDB customerDB) {
        Controller controller = new Controller(printer, eas, eis, customerDB);

        try {
            controller.addItem(1);
            assert(false);
        } catch(NullPointerException e) {
            assert(true);
        }

        controller.initiateSale();
        
        try {
            controller.addItem(1);
            assert(true);
        } catch(Exception e){
            assert(false);
        }
    }
    
    public static void addItemTest(Printer printer, EAS eas, EIS eis, CustomerDB customerDB) {
        Controller controller = new Controller(printer, eas, eis, customerDB);

        controller.initiateSale();
        controller.addItem(1);
        assert(controller.endSale() == 2);

        controller.initiateSale();
        controller.addItem(99); //Shouldn't exist
        assert(controller.endSale() == 0);

        controller.initiateSale();
        controller.addItem(1);
        controller.addItem(3, 5);
        assert(controller.endSale() == 27);

        controller.initiateSale();
        controller.addItem(1);
        controller.addItem(99, 2); //Shouldn't exist
        assert(controller.endSale() == 2);
    }

    public static void endSaleTest(Printer printer, EAS eas, EIS eis, CustomerDB customerDB) {
        Controller controller = new Controller(printer, eas, eis, customerDB);

        controller.initiateSale();
        assert(controller.endSale() == 0);

        controller.addItem(1,5);
        assert(controller.endSale() == 10);
    }
    
    public static void discountRequestTest(Printer printer, EAS eas, EIS eis, CustomerDB customerDB) {
        Controller controller = new Controller(printer, eas, eis, customerDB);
        controller.initiateSale();

        controller.addItem(1,10);
        int totalPrice = controller.endSale();
        int priceAfterDiscount = controller.discountRequest(68);
        assert(totalPrice == priceAfterDiscount);

        priceAfterDiscount = controller.discountRequest(100);
        assert(totalPrice/2 == priceAfterDiscount);
    }

    public static void paymentTest(Printer printer, EAS eas, EIS eis, CustomerDB customerDB) {
        Controller controller = new Controller(printer, eas, eis, customerDB);

        controller.initiateSale();
        controller.addItem(1);
        int totalPrice = controller.endSale();
        int change = controller.payment(totalPrice + 1);
        assert(change == 1);
    }

    public static void main(String[] args) {
        CustomerDB customerDB = new CustomerDB();
        EIS eis = new EIS();
        EAS eas = new EAS();
        Printer printer = new Printer();

        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b){}
        }));
        
        initiateSaleTest(printer, eas, eis, customerDB);
        addItemTest(printer, eas, eis, customerDB);
        endSaleTest(printer, eas, eis, customerDB);
        discountRequestTest(printer, eas, eis, customerDB);
        paymentTest(printer, eas, eis, customerDB);
    }
}
