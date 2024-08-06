package org.example;

public class Rhombus implements Shape{

    private double internalSide_1;
    private double internalSide_2;

    private double side;

    public Rhombus(double side){
        this.side = side;
    }

    public Rhombus(double side_1, double side_2) {
        this.internalSide_1 = side_1;
        this.internalSide_2 = side_2;

    }

    public double getInternalSide_1() {
        return internalSide_1;
    }

    public void setInternalSide_1(double internalSide_1) {
        this.internalSide_1 = internalSide_1;
    }

    public double getInternalSide_2() {
        return internalSide_2;
    }

    public void setInternalSide_2(double internalSide_2) {
        this.internalSide_2 = internalSide_2;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return (getInternalSide_1() * getInternalSide_2()) / 2;
    }

    @Override
    public double perimeter() {
        return getSide() * 4;
    }

    @Override
    public void draw() {

    }

    public String toString(){
        return getClass().getSimpleName();
    }
}
