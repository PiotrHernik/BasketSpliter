package com.ocado.basket;

import com.ocado.basket.basketCover.MinimumSupplierFinder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MinimumSupplierFinderTest {
    @Test
    public void testSplit() {
        //Test sprawdza czy funckja split zwraca minimalną ilość dostawców
        List<String> universe = Arrays.asList("item1", "item2", "item3", "item4");
        Map<String, List<String>> suppliersMap = new HashMap<>();
        suppliersMap.put("supplier1", Arrays.asList("item1", "item2"));
        suppliersMap.put("supplier2", Arrays.asList("item3", "item4"));
        suppliersMap.put("supplier3", Arrays.asList("item1", "item2", "item3", "item4"));
        MinimumSupplierFinder finder = new MinimumSupplierFinder(universe, suppliersMap);


        List<List<String>> result = finder.split();

        assertEquals(Arrays.asList(Arrays.asList("supplier3")), result);
    }
}