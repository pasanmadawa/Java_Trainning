package org.example;

public class Circle implements Shape {

    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }


    @Override
    public double area() {
        return Math.PI * getRadius() * getRadius();
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * getRadius();
    }

    @Override
    public void draw() {

    }

    @Override
    public String toString(){
        return getClass().getSimpleName();
    }
}
