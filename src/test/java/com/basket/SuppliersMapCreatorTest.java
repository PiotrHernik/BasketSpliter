package com.basket;

import com.basket.basketCover.SuppliersMapCreator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SuppliersMapCreatorTest {
    @Test
    public void testCreateSuppliersMap() {
        List<String> universe = Arrays.asList("item1", "item2", "item3", "item4");
        Map<String, List<String>> config = new HashMap<>();

        config.put("item1", Arrays.asList("supplier1", "supplier3"));
        config.put("item2", Arrays.asList("supplier1", "supplier2"));
        config.put("item3", Arrays.asList("supplier3"));
        config.put("item4", Arrays.asList("supplier2"));

        Map<String, List<String>> expected = new HashMap<>();
        expected.put("supplier1", Arrays.asList("item1", "item2"));
        expected.put("supplier2", Arrays.asList("item2", "item4"));
        expected.put("supplier3", Arrays.asList("item1", "item3"));

        SuppliersMapCreator creator = new SuppliersMapCreator(universe, config);
        Map<String, List<String>> result = creator.createSuppliersMap();

        assertEquals(expected, result);


    }
}