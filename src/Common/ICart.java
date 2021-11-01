package Common;

public interface ICart {
    ICart getInstance();
    void addToCart();
    void removeFromCart();
    int getTotalProducts();
    double getTotalPrice();
    void completeOrder();
}
