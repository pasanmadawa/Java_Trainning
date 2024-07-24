package org.example;

public class Triangle implements Shape {

    private double base;
    private double height;

    private double length1;
    private double length2;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public Triangle(double base, double length1, double length2){
        this.base = base;
        this.length1 = length1;
        this.length2 = length2;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength1() {
        return length1;
    }

    public void setLength1(double length1) {
        this.length1 = length1;
    }

    public double getLength2() {
        return length2;
    }

    public void setLength2(double length2) {
        this.length2 = length2;
    }

    @Override
    public double area() {
        return (getBase() * getHeight()) / 2;
    }

    @Override
    public double perimeter() {
        return getBase() + getLength1() + getLength2();
    }

    @Override
    public String toString(){
        return getClass().getSimpleName();
    }
}
