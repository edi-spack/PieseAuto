package Common;

public class AutoPart {
    private String id;
    private String name;
    private String brand;
    private String model;
    private double price;
    private int stock;

    public AutoPart(String id, String name, String brand, String model, double price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stock = stock;
    }

    //id
    public String getId(){
        return id;
    }

    //name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //brand
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    //model
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }

    //price
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    //stock
    public int getStock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
}
