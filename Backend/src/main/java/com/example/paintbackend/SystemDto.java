package com.example.paintbackend;

import java.util.ArrayList;
public class SystemDto {
    static ArrayList<ParentShape> system = new ArrayList<>();
    public ArrayList<ParentShape> getSystem() {
        return system;
    }
    public void setSystem(ArrayList<ParentShape> system) {
        this.system = system;
    }
}
