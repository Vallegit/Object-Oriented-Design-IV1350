package tests;

import integration.*;
import model.Sale;

import java.io.OutputStream;
import java.io.PrintStream;

public class EISTest {
    public static void getItemTest(EIS eis) {
        Item item;
        try{
            item = eis.getItem(1);
        }catch(Exception e){
            assert(false);
            return;
        }
        
        //Borde fungera oavsett
        assert(item.getDescription() == "toilet paper");
        assert(item.getPrice() == 1);
        assert(item.getVat() == 1);
    }

    public static void checkValidTest(EIS eis) {
        for (int i = 0; i < 5 ; i++){
            assert(eis.checkValid(i));
        }
        assert(eis.checkValid(-1) == false);
        assert(eis.checkValid(5) == false);
    }

    public static void exceptionTest(EIS eis) {
        try {
            eis.getItem(7);
            assert(false);
        } catch(ItemNotFoundException e) {
            assert(true);
        } catch(Exception e) {
            assert(false);
        }
        
        try {
            eis.getItem(42069);
            assert(false);
        } catch(DatabaseNotRespondingException e) {
            assert(true);
        } catch(Exception e) {
            assert(false);
        }
    }

    public static void main(String[] args) {
        EIS eis = new EIS();

        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b){}
        }));

        getItemTest(eis);
        checkValidTest(eis);
        exceptionTest(eis);
    }
}
