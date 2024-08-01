package org.example;

public class Clothing extends Items{

    private String colour;
    private String clotheType;
    private String size;

    public Clothing(String productId,String name, double price, String brand, int quantity) {
        super( productId,name, price, brand, quantity);
    }

    public Clothing(String productId,String name, double price, String brand, int quantity,String colour, String clotheType,String size) {
        super( productId,name, price, brand, quantity);
        this.colour = colour;
        this.clotheType = clotheType;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getClotheType() {
        return clotheType;
    }

    public void setClotheType(String clotheType) {
        this.clotheType = clotheType;
    }

    public String toString() {
        return "Clothing = {" +
                "productId='" + getProductId() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", brand='" + getBrand() + '\'' +
                ", quantity=" + getQuantity() +
                ", color=" + getColour() + '\''+
                ", clothe type=" + getClotheType() +'\''+
                ", clothe size=" + getSize() +'\''+
                '}';
    }
}
