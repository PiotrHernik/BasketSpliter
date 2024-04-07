package org.example;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.*;

public class BasketSplitter {
    private final Map<String, List<String>> config;

    public BasketSplitter(String absolutePathToConfigFile) {
        IJsonReader jsonReader = new JsonReader();
        try{
            this.config = jsonReader.readFromJson(absolutePathToConfigFile, new TypeReference<Map<String, List<String>>>(){});
            if(config == null || config.isEmpty() || config.size() > 1000){
                throw new IllegalArgumentException("The config JSON is empty or null");
            }
        } catch (IOException e){
            throw new RuntimeException("An error occurred while reading the config JSON: " + e.getMessage());
        }
    }

    public Map<String, List<String>> split(List<String> items) throws IllegalArgumentException{
        if(items == null || items.isEmpty() || items.size() > 100){
            throw new IllegalArgumentException("The list of items cannot be null or empty or is to big");
        }
        int maxValOfRanking = 0;

        SuppliersMapCreator suppliersMapCreator = new SuppliersMapCreator(items, config);
        Map<String, List<String>> suppliersMap = suppliersMapCreator.createSuppliersMap();
        if(suppliersMap == null || suppliersMap.isEmpty() || suppliersMap.size() > 10){
            throw new IllegalArgumentException("The suppliers map is empty or null");
        }

        CoverBasket minimumSuppliers = new MinimumSupplierFinder(items, suppliersMap);
        List<List<String>> listOfMinSuppliers = minimumSuppliers.split();

        DivisionProducts divisionProducts = new BigestBasket(listOfMinSuppliers, suppliersMap, maxValOfRanking);
        return divisionProducts.split();

    }
}

