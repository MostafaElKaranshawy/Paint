package com.example.paintbackend;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(as = Polygon.class)
public class Polygon extends ParentShape{
    private int sides;
    private double radius;
    Polygon(){}
    Polygon(String name, int sides ,double x, double y){
        super(name,sides==4?"square":"tri",x, y);
        this.sides = sides;
        this.radius = 100;
    }
    public int getSides() {
        return sides;
    }
    public void setSides(int sides) {
        this.sides = sides;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public ParentShape copy(){
        Polygon copy=new Polygon(this.name, this.sides, this.x + 50, this.y + 50);
        super.copy(copy);
        copy.radius=this.radius;
        return copy;
    }
}