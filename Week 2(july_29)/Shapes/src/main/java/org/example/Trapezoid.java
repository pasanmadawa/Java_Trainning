package org.example;

public class Trapezoid implements Shape {

    private double parallelSide_1;
    private double parallelSide_2;

    private double height;

    private double side_3;
    private double side_4;

    public Trapezoid(double parallelSide_1, double parallelSide_2, double height) {
        this.parallelSide_1 = parallelSide_1;
        this.parallelSide_2 = parallelSide_2;
        this.height = height;
    }

    public Trapezoid(double parallelSide_1, double parallelSide_2, double side_3, double side_4) {
        this.parallelSide_1 = parallelSide_1;
        this.parallelSide_2 = parallelSide_2;
        this.side_3 = side_3;
        this.side_4 = side_4;
    }

    public double getParallelSide_1() {
        return parallelSide_1;
    }

    public void setParallelSide_1(double parallelSide_1) {
        this.parallelSide_1 = parallelSide_1;
    }

    public double getParallelSide_2() {
        return parallelSide_2;
    }

    public void setParallelSide_2(double parallelSide_2) {
        this.parallelSide_2 = parallelSide_2;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSide_3() {
        return side_3;
    }

    public void setSide_3(double side_3) {
        this.side_3 = side_3;
    }

    public double getSide_4() {
        return side_4;
    }

    public void setSide_4(double side_4) {
        this.side_4 = side_4;
    }

    @Override
    public double area() {
        return ((getParallelSide_1() + getParallelSide_2()) * getHeight()) / 2;
    }

    @Override
    public double perimeter() {
        return getParallelSide_1() + getParallelSide_2() + getSide_3() + getSide_4();
    }

    public String toString(){
        return getClass().getSimpleName();
    }
}
