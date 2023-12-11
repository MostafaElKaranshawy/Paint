package com.example.paintbackend;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
public class ShapeDer extends JsonDeserializer<ParentShape> {
    @Override
    public ParentShape deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ObjectNode root = mapper.readTree(p);
        if (root.has("shapeType") && root.get("shapeType").asText().equals("circle")) {
            return mapper.readValue(root.toString(), Circle.class);
        }
        if (root.has("shapeType") && (root.get("shapeType").asText().equals("square")||root.get("shapeType").asText().equals("tri"))) {
            return mapper.readValue(root.toString(), Polygon.class);
        }
        if (root.has("shapeType") && root.get("shapeType").asText().equals("rect")) {
            return mapper.readValue(root.toString(), Rectangle.class);
        }
        if (root.has("shapeType") && root.get("shapeType").asText().equals("ellipse")) {
            return mapper.readValue(root.toString(), Ellipse.class);
        }
        return mapper.readValue(root.toString(), Line.class);
    }
}