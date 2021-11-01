package Common;

import Exceptions.DatabaseException;

public interface ICart {
    static ICart getInstance() {
        return null;
    }

    void addToCart(AutoPart part);
    void removeFromCart(int index);
    int getTotalProducts();
    double getTotalPrice();
    void completeOrder() throws DatabaseException;
}
