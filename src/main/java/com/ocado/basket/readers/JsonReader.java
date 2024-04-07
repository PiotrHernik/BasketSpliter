package com.ocado.basket.readers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

            return objectMapper.readValue(jsonContent, type);

        }catch (JsonParseException e) {
            throw new IOException("Invalid json file", e);
        }catch(MismatchedInputException e) {
            throw new IOException("Mismatched JSON input. Cannot deserialize value of type " + type.getType() + " from Object value", e);
        }catch(JsonMappingException e) {
            throw new IOException("Failed to map JSON to type: " + type, e);
        }
    }
}