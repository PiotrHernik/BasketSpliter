//package com.ocado.basket;
//import com.fasterxml.jackson.core.type.TypeReference;
//
//import java.io.IOException;
//import java.util.*;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//
//        try {
//            BasketSplitter basketSplitter = new BasketSplitter("C:\\Users\\Piotr\\Downloads\\Zadanie\\Zadanie\\config.json");
//            IJsonReader jsonReader = new JsonReader();
//
//            List<String> items = null;
//            items = jsonReader.readFromJson("C:\\Users\\Piotr\\Downloads\\Zadanie\\Zadanie\\basket-1.json", new TypeReference<List<String>>() {
//            });
//            Map<String, List<String>> optimalDistribution = basketSplitter.split(items);
//            System.out.println(optimalDistribution);
//        } catch (IllegalArgumentException e) {
//            System.err.println("An error occurred while splitting the basket: " + e.getMessage());
//        } catch (IOException e) {
//            System.err.println("An error occurred while reading the JSON: " + e.getMessage());
//        }
//
//
//
//
//    }
//}
//
//
//
//
//
//
//
