package com.example.paintbackend;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Line.class)
public class Line extends ParentShape{
//    private double x2, y2;
    private double[] points = new double[4];
    Line(){}
    Line(String name, double x, double y){
        super(name,"line", x, y);
        this.strokeWidth = 5;
        this.setPoints();
    }
    public void setPoints() {
        this.points[0] = 0;
        this.points[1] = 0;
        this.points[2] = 200;
        this.points[3] = 0;
    }
    public double[] getPoints() {
        return this.points;
    }
    public ParentShape copy(){
        Line copy=new Line(this.name, this.x + 50, this.y + 50);
        super.copy(copy);
        copy.points[2] = this.points[2];
        copy.points[3] = this.points[3];
        return copy;
    }
}
