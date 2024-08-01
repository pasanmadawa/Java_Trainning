package org.example;

public class Items {

    private String name;
    private double price;
    private String brand;
    private int quantity;
    private String productId;

    public Items(String productId,String name, double price, String brand, int quantity ) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                ", productId='" + productId + '\'' +
                '}';
    }
}
