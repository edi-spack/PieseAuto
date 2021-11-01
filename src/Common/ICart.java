package Common;

public interface ICart {
    static ICart getInstance() {
        return null;
    }

    void addToCart(AutoPart part);
    void removeFromCart(AutoPart part);
    int getTotalProducts();
    double getTotalPrice();
    void completeOrder();
}
