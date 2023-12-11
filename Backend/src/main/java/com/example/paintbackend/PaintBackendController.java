package com.example.paintbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class PaintBackendController {
    @Autowired
    private PaintBackendService service;
    @PostMapping("/change")
    public void takeChange(@RequestBody SystemDto system){
        service.saveChanges(system);
    }
    @PostMapping("/createShape")
    public ParentShape createShape(@RequestBody Receiver data){
        return service.getShape(data);
    }
    @PostMapping("/clear")
    public void clear(){
        service.clear();
    }
    @PostMapping("/undo")
    public SystemDto undo(){
        ArrayList<ParentShape> tempSystem= service.undo();
        SystemDto dto = new SystemDto();
        dto.setSystem(tempSystem);
        return dto;
    }
    @PostMapping("/redo")
    public SystemDto redo(){
        ArrayList<ParentShape> tempSystem= service.redo();
        SystemDto dto = new SystemDto();
        dto.setSystem(tempSystem);
        return dto;
    }
    @PostMapping("/copy")
    public ParentShape copy(@RequestBody ParentShape shapeToCopy){
        return service.copyShape(shapeToCopy);
    }
    @PostMapping("/saveToJson")
    public boolean save(@RequestBody PathDto pathDto){
        return service.saveToJson(pathDto);
    }
    @PostMapping("/loadFromJson")
    public SystemDto loadFromJson(@RequestBody PathDto pathDto){
        SystemDto toBeSent = null;
        toBeSent = service.loadFromJsonFile(pathDto);
        return toBeSent;
    }
    @PostMapping("/saveToXML")
    public boolean saveToXML(@RequestBody PathDto pathDto){
        return service.saveToXML(pathDto);
    }
    @PostMapping("/loadFromXML")
    public SystemDto loadFromXML(@RequestBody PathDto pathDto){
        SystemDto toBeSent = null;
        toBeSent = service.loadFromXMLFile(pathDto);
        return toBeSent;
    }
}