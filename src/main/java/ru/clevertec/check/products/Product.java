package main.java.ru.clevertec.check.products;

/**
 * @author Tatiana Futarnaya
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private boolean wholesaleProduct=false;

    public Product() {
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }



    public Product(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Product(int id, String name, double price, int quantity, boolean wholesaleProduct) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.wholesaleProduct = wholesaleProduct;
    }

    public double calculateCost(int quantity) {
        return price *  quantity;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isWholesaleProduct() {
        return wholesaleProduct;
    }

    public void setQuantity(int quantity) {
        if(quantity>=0){
            this.quantity = quantity;
        }else{
            this.quantity += quantity;
        }

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", wholesaleProduct=" + wholesaleProduct +
                '}';
    }
}
