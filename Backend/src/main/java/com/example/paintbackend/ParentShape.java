package com.example.paintbackend;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ShapeDer.class)
public abstract class ParentShape {
    protected String name;
    protected double scaleX;
    protected double scaleY;
    protected double rotation;
    protected String shapeType;
    protected double x,y;
    protected String fill;
    protected String stroke;
    protected int strokeWidth;
    protected boolean draggable;
    protected boolean visible;
    ParentShape(){}
    public ParentShape(String name ,String shapeType, double x, double y) {
        this.name = name;
        this.shapeType = shapeType;
        this.x = x;
        this.y = y;
        this.fill = "white";
        this.stroke = "black";
        this.strokeWidth = 2;
        this.draggable = true;
        this.visible = true;
        this.scaleX = 1;
        this.scaleY = 1;
        this.rotation = shapeType=="square"?45:0;
    }
    public void copy(ParentShape copy){
        copy.setDraggable(this.draggable);
        copy.setFill(this.fill);
        copy.setStroke(this.stroke);
        copy.setStrokeWidth(this.strokeWidth);
        copy.setScaleX(this.scaleX);
        copy.setScaleY(this.scaleY);
        copy.setRotation(this.rotation);
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public String getFill() {
        return fill;
    }
    public void setFill(String fill) {
        this.fill = fill;
    }
    public String getStroke() {
        return stroke;
    }
    public void setStroke(String stroke) {
        this.stroke = stroke;
    }
    public int getStrokeWidth() {
        return strokeWidth;
    }
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
    public boolean isDraggable() {
        return draggable;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }
    public String getShapeType() {
        return shapeType;
    }
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getScaleX() {
        return scaleX;
    }
    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }
    public double getScaleY() {
        return scaleY;
    }
    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }
    public double getRotation() {
        return rotation;
    }
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
    protected abstract ParentShape copy();
}
