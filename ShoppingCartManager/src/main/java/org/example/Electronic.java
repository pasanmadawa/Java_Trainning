package org.example;

public class Electronic extends Items{

    private String warrantyPeriod;

    public Electronic(String productId,String name,double price, String brand, int quantity) {
        super( productId,name, price, brand, quantity);
    }

    public Electronic(String productId,String name, double price, String brand, int quantity, String warrantyPeriod) {
        super( productId,name,price, brand, quantity);
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String toString() {
        return "Electronics = {" +
                "productId='" + getProductId() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", brand='" + getBrand() + '\'' +
                ", quantity=" + getQuantity() +
                ", warranty period=" + getWarrantyPeriod() +
                '}';
    }
}
