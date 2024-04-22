package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ocado.basket.readers.IJsonReader;
import com.ocado.basket.readers.JsonReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

        @Test
        void mainTest() {
            try {
                BasketSplitter basketSplitter = new BasketSplitter("src/test/resources/config.json");
                IJsonReader jsonReader = new JsonReader();

                List<String> items1 = null;
                List<String> items2 = null;
                items1 = jsonReader.readFromJson("src/test/resources/basket-1.json", new TypeReference<List<String>>() {});
                items2 = jsonReader.readFromJson("src/test/resources/basket-2.json", new TypeReference<List<String>>() {});
                Map<String, List<String>> result1 = basketSplitter.split(items1);
                Map<String, List<String>> result2 = basketSplitter.split(items2);

                System.out.println(result2);

                Map<String, List<String>> expected1 = new HashMap<>();
                expected1.put("Courier", Arrays.asList("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White",
                        "Flower - Daisies", "Cookies - Englishbay Wht"));
                expected1.put("Express Collection", Arrays.asList("Fond - Chocolate"));

                Map<String, List<String>> expected2 = new HashMap<>();
                expected2.put("Same day delivery", Arrays.asList("Sauce - Mint", "Numi - Assorted Teas", "Garlic - Peeled"));
                expected2.put("Courier", Arrays.asList("Cake - Miini Cheesecake Cherry"));
                expected2.put("Express Collection", Arrays.asList("Fond - Chocolate", "Chocolate - Unsweetened",
                        "Nut - Almond, Blanched, Whole", "Haggis", "Mushroom - Porcini Frozen", "Longan",
                        "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear", "Puree - Strawberry", "Apples - Spartan",
                        "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea"));

                assertEquals(expected1, result1);
                assertEquals(expected2, result2);
            } catch (IllegalArgumentException e) {
                System.err.println("An error occurred while splitting the basket: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("An error occurred while reading the JSON: " + e.getMessage());
            }
        }

}