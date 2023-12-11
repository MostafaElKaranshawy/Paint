package com.example.paintbackend;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(as = Ellipse.class)
public class Ellipse extends ParentShape{
    private double radiusX;
    private double radiusY;

    Ellipse() {}

    Ellipse(String name, double x, double y){
        super(name,"ellipse", x, y);
        this.radiusX = 150;
        this.radiusY = 100;
    }
    public double getRadiusX() {
        return radiusX;
    }
    public double getRadiusY() {
        return radiusY;
    }
    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }
    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }
    public ParentShape copy(){
        Ellipse copy=new Ellipse(this.name, this.x + 50, this.y + 50);
        super.copy(copy);
        copy.radiusX=this.radiusX;
        copy.radiusY=this.radiusY;
        return copy;
    }
}
