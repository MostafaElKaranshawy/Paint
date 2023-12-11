package com.example.paintbackend;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(as = Circle.class)
public class Circle extends ParentShape{
    private double radius;
    Circle(){}
    Circle(String name ,double x, double y){
        super(name,"circle", x, y);
        this.radius = 100;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public ParentShape copy(){
        Circle copy=new Circle(this.name, this.x + 50, this.y + 50);
        super.copy(copy);
        copy.radius=this.radius;
        return copy;
    }
}