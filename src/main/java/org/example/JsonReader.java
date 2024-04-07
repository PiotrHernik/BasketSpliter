package org.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class JsonReader implements IJsonReader {
    private final ObjectMapper objectMapper;

    public JsonReader() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T readFromJson(String path, TypeReference<T> type) throws IOException {
        if (!path.endsWith(".json")) {
            throw new IOException("Invalid file type. Expected a .json file");
        }

        try{
            String jsonContent = new String(Files.readAllBytes(Paths.get(path)));
            if(jsonContent.isEmpty()){
                throw new IOException("Basket is empty");
            }
//            if (type.getType().equals(new TypeReference<List<String>>(){}.getType())) {
//                // Check if the JSON is not a list
//                if (!jsonContent.trim().startsWith("[")) {
//                    throw new IOException("Mismatched JSON input. Expected a list but found a different type");
//                }
//            } else if (type.getType().equals(new TypeReference<Map<String, List<String>>>(){}.getType())) {
//                // Check if the JSON is not a map
//                if (!jsonContent.trim().startsWith("{")) {
//                    throw new IOException("Mismatched JSON input. Expected a map but found a different type");
//                }
//            } else {
//                throw new IOException("Invalid type. Expected a List or Map");
//            }


            return objectMapper.readValue(jsonContent, type);

        }catch (JsonParseException e) {
            throw new IOException("Invalid json file", e);
        }catch(MismatchedInputException e) {
//            System.err.println("Mismatched JSON input. Cannot deserialize value of type " + type.getType() + " from Object value: " + e.getMessage());
//            return null;
            throw new IOException("Mismatched JSON input. Cannot deserialize value of type " + type.getType() + " from Object value", e);
        }catch(JsonMappingException e) {
            throw new IOException("Failed to map JSON to type: " + type, e);
        }
    }
}