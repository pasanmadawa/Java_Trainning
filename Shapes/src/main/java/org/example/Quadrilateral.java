package org.example;

public class Quadrilateral implements Shape{

    private int height;
    private int width;

    public Quadrilateral(int height, int width){
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int perimeter(){
        return (getHeight()  + getWidth()) * 2;
    }

    @Override
    public int area(){
        return getHeight() * getWidth();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
