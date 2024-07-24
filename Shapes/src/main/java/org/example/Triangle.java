package org.example;

public class Triangle implements Shape {

    private int base;
    private int height;

    private int length1;
    private int length2;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    public Triangle(int base, int length1, int length2){
        this.base = base;
        this.length1 = length1;
        this.length2 = length2;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength1() {
        return length1;
    }

    public void setLength1(int length1) {
        this.length1 = length1;
    }

    public int getLength2() {
        return length2;
    }

    public void setLength2(int length2) {
        this.length2 = length2;
    }

    @Override
    public int area() {
        return (getBase() * getHeight()) / 2;
    }

    @Override
    public int perimeter() {
        return getBase() + getLength1() + getLength2();
    }

    public String toString(){
        return getClass().getSimpleName();
    }
}
