package Common;

import Database.SQLiteInterface;
import Exceptions.DatabaseException;

import java.util.ArrayList;

public class Cart implements ICart{

    private static Cart instance;
    private ArrayList<AutoPart> partsList;

    private Cart() {
        partsList = new ArrayList<>();
    }

    public static Cart getInstance() {
        if(instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addToCart(AutoPart product) {
        partsList.add(product);
    }

    public void removeFromCart(int index) {
        partsList.remove(index);
    }

    public int getTotalProducts() {
        return partsList.size();
    }

    public double getTotalPrice() {
        double result = 0.0;

        for (AutoPart part: partsList) {
            result += part.getPrice();
        }

        return result;
    }

    public void completeOrder() throws DatabaseException {

        SQLiteInterface database = new SQLiteInterface();

        for (AutoPart part: partsList) {
            database.updateStock(part.getId(), 1);
        }

        partsList.clear();
    }

    public ArrayList<String[]> getCartItems() {
        ArrayList<String[]> result = new ArrayList<>();

        for (AutoPart part: partsList) {
            String[] temp = {part.getName(), part.getBrand(), part.getModel(), Double.toString(part.getPrice())};
            result.add(temp);
        }

        return result;
    }
}