package com.example.paintbackend;

public class ShapeFactory {
    public static ParentShape createShape(Receiver data){
        String shapeType = data.getShapeType();
        ParentShape shape = null;
        if(shapeType.equals("circle")){
            shape = new Circle(data.getName(), data.getX(), data.getY());
        }
        else if(shapeType.equals("tri")){
            shape = new Polygon(data.getName(), 3 ,data.getX(), data.getY());
        }
        else if(shapeType.equals("square")){
            shape = new Polygon(data.getName(), 4 ,data.getX(), data.getY());
        }
        else if(shapeType.equals("rect")){
            shape = new Rectangle(data.getName(), data.getX(), data.getY());
        }
        else if(shapeType.equals("ellipse")){
            shape = new Ellipse(data.getName(), data.getX(), data.getY());
        }
        else if(shapeType.equals("line")){
            shape = new Line(data.getName(), data.getX(), data.getY());
        }
        return shape;
    }
}
