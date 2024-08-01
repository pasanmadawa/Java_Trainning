package org.example;

public class Educational extends Items {

    private int discount;


    public Educational(String productId,String name,double price, String brand, int quantity) {
        super( productId,name,price, brand, quantity);
    }

    public Educational(String productId,String name,double price, String brand, int quantity,int discount) {
        super( productId,name, price, brand, quantity);
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String toString() {
        return "Educational = {" +
                "productId='" + getProductId() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", brand='" + getBrand() + '\'' +
                ", quantity=" + getQuantity() +
                ", discount=" + getDiscount() +
                '}';
    }
}
