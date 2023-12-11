package com.example.paintbackend;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Rectangle.class)
public class Rectangle extends ParentShape{
    private double width;
    private double height;
    Rectangle(){}
    Rectangle(String name, double x, double y){
        super(name, "rect",x, y);
        this.width = 200;
        this.height=150;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getWidth() {
        return width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getHeight() {
        return height;
    }
    public ParentShape copy(){
        Rectangle copy=new Rectangle(this.name, this.x + 50, this.y + 50);
        super.copy(copy);
        copy.width=this.width;
        copy.height=this.height;
        return copy;
    }
}