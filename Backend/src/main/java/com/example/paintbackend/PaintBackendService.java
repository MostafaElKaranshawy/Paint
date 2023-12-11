package com.example.paintbackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
@Service
public class PaintBackendService {
    private static PaintBackendService service;
    private Stack<ArrayList<ParentShape>> undoStack = new Stack<>();
    private Stack<ArrayList<ParentShape>> redoStack = new Stack<>();
    public void saveChanges(SystemDto system){
        undoStack.push(system.getSystem());
        redoStack.clear();
    }
    public ParentShape getShape(Receiver data){
        ShapeFactory shapeFactory = new ShapeFactory();
        ParentShape shape = ShapeFactory.createShape(data);
        ArrayList<ParentShape> newContainer;
        if(undoStack.isEmpty()){
            newContainer = new ArrayList<>();
            undoStack.push(newContainer);
        }

        newContainer = new ArrayList<>(undoStack.peek());
        newContainer.add(shape);
        undoStack.push(newContainer);
        redoStack.clear();
        return shape;
    }
    public void clear(){
        ArrayList<ParentShape> newSystem = new ArrayList<>();
        undoStack.push(newSystem);
        redoStack.clear();
    }
    public ArrayList<ParentShape> undo(){
        ArrayList<ParentShape> tempSystem;
        if(undoStack.isEmpty()){
            tempSystem = new ArrayList<ParentShape>();
            undoStack.push(tempSystem);
            return tempSystem;
        }
        else if(undoStack.size()==1){
            return undoStack.peek();
        }
        redoStack.push(undoStack.pop());
        return undoStack.peek();
    }
    public ArrayList<ParentShape> redo(){
        ArrayList<ParentShape> tempSystem;
        if(undoStack.isEmpty()){
            tempSystem = new ArrayList<ParentShape>();
            undoStack.push(tempSystem);
            return tempSystem;
        }
        else if(redoStack.isEmpty())
            return undoStack.peek();
        undoStack.push(redoStack.peek());
        return redoStack.pop();
    }
    public ParentShape copyShape(ParentShape oldShape){
        ParentShape newShape = oldShape.copy();
        ArrayList<ParentShape> newContainer = new ArrayList<>(undoStack.peek());
        newContainer.add(newShape);
        undoStack.push(newContainer);
        return newShape;
    }
    public boolean saveToJson(PathDto pathDto)  {
        String jsonData;
        try {
            jsonData = convertToJsonString();
            writeInJsonFile(jsonData, pathDto.getPathToAccess());
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public SystemDto loadFromJsonFile(PathDto pathDto){
        String jsonData;
        ArrayList<ParentShape> currentSystem = null;
        SystemDto systemDto = new SystemDto();
        try {
            jsonData = readFromJsonFile(pathDto.getPathToAccess());
            currentSystem = new ArrayList<ParentShape>(convertToJavaArrayList(jsonData));
        }catch (Exception e) {
            systemDto.setSystem(null);
            return systemDto;
        }
        systemDto.setSystem(currentSystem);
        redoStack.clear();
        undoStack.clear();
        undoStack.push(currentSystem);
        return systemDto;
    }
    private String convertToJsonString() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<ParentShape> toBeSaved;
        if(undoStack.isEmpty()){
            toBeSaved = new ArrayList<>();
        }
        else{
            toBeSaved = new ArrayList<>(undoStack.peek());
        }
        String jsonData = mapper.writeValueAsString(toBeSaved);
        return jsonData;
    }
    private void writeInJsonFile(String jsonData, String pathToSaveIn) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(pathToSaveIn),jsonData);
    }
    private String readFromJsonFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.readValue(new File(path), String.class);
        return jsonData;
    }
    private ArrayList<ParentShape> convertToJavaArrayList(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,true);
        ParentShape[] tempArr;

        tempArr = mapper.readValue(jsonData, ParentShape[].class);
        ArrayList<ParentShape> loadedSystem = new ArrayList<>(Arrays.asList(tempArr));

        return loadedSystem;
    }
    public boolean saveToXML(PathDto pathDto){
        XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathDto.getPathToAccess()), "ISO-8859-1"))) {
            ArrayList<ParentShape> toBeSaved;
            if(undoStack.isEmpty())
                toBeSaved = new ArrayList<>();
            else
                toBeSaved = new ArrayList<>(undoStack.peek());
            xstream.toXML(toBeSaved, writer);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public SystemDto loadFromXMLFile(PathDto pathDto){
        XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
        ArrayList<ParentShape> loadedSystem = new ArrayList<>();
        SystemDto currentSystem = new SystemDto();
        xstream.allowTypesByWildcard(new String[] {
                "com.example.paintbackend.*"
        });
        try (Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathDto.getPathToAccess()), "ISO-8859-1"))) {
            loadedSystem = (ArrayList<ParentShape>) xstream.fromXML(reader);
        }catch (Exception e) {
            currentSystem.setSystem(null);
            return currentSystem;
        }
        currentSystem.setSystem(loadedSystem);

        redoStack.clear();
        undoStack.clear();
        undoStack.push(loadedSystem);
        return currentSystem;
    }
}