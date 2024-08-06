package org.example;

public class Quadrilateral implements Shape{

    private double height;
    private double width;

    public Quadrilateral(double height, double width){
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double perimeter(){
        return (getHeight()  + getWidth()) * 2;
    }

    @Override
    public void draw() {
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                System.out.print("*" + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public double area(){
        return getHeight() * getWidth();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
