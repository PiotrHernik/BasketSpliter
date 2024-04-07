package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ocado.basket.basketCover.CoverBasket;
import com.ocado.basket.basketCover.MinimumSupplierFinder;
import com.ocado.basket.basketCover.SuppliersMapCreator;
import com.ocado.basket.productsDivision.BigestBasket;
import com.ocado.basket.productsDivision.DivisionProducts;
import com.ocado.basket.readers.IJsonReader;
import com.ocado.basket.readers.JsonReader;

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

    public Map<String, List<String>> split(List<String> items) throws IllegalArgumentException, IOException {
        try{
            if(items == null || items.isEmpty() || items.size() > 100){
                throw new IllegalArgumentException("The list of items cannot be null or empty or is to big");
            }
            int maxValOfRanking = 0;

            SuppliersMapCreator suppliersMapCreator = new SuppliersMapCreator(items, config);

            Map<String, List<String>> suppliersMap = suppliersMapCreator.createSuppliersMap();
            if(suppliersMap.size() > 10){
                throw new IOException("The suppliers map is empty or null");
            }

            CoverBasket minimumSuppliers = new MinimumSupplierFinder(items, suppliersMap);
            List<List<String>> listOfMinSuppliers = minimumSuppliers.split();

            DivisionProducts divisionProducts = new BigestBasket(listOfMinSuppliers,
                                                                    suppliersMap,
                                                                    maxValOfRanking);
            return divisionProducts.split();

        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(
                    "An error occurred while splitting the basket: " + e.getMessage());
        }catch (IOException e){
            throw new IOException(
                    "An error occurred while splitting the basket: " + e.getMessage());
        }


    }
}

