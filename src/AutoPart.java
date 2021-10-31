public class AutoPart {
    private String name;
    private String brand;
    private String model;
    private String id;
    private double price;
    private int stock;

    public AutoPart(String name, String brand, String model, String id, double price, int stock) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stock = stock;
    }

    //name
    private String getName(){
        return name;
    }
    private void setName(String name){
        this.name = name;
    }

    //brand
    private String getBrand(){
        return brand;
    }
    private void setBrand(String brand){
        this.brand = brand;
    }

    //model
    private String getModel(){
        return model;
    }
    private void setModel(String model){
        this.model = model;
    }

    //id
    private String getId(){
        return id;
    }

    //price
    private double getPrice(){
        return price;
    }
    private void setPrice(double price){
        this.price = price;
    }

    //stock
    private int getStock(){
        return stock;
    }
    private void setStock(int stock){
        this.stock = stock;
    }
}
