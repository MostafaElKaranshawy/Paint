package com.example.paintbackend;
public class Receiver{
    private String name;
    private String shapeType;
    private double x;
    private double y;
    public String getShapeType() {
        return shapeType;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}