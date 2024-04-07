package com.ocado.basket;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MinSupplierFinderTest {
    @Test
    public void testMinAmountOfSuppliers() {

//        Test ma na celu sprawdzenie odzyskania listy dostawców dla najkrótszych wybranych podzbiorów.
//        Podzbiory produktów, są podzbiorami koszyka, dostarczanymi przez jednego dostawcę.
        Set<List<Set<String>>> shortestSelectedSubsets = new HashSet<>();
        shortestSelectedSubsets.add(Arrays.asList(new HashSet<>(Arrays.asList("item1", "item2")), new HashSet<>(Arrays.asList("item3", "item4"))));
        Map<String, List<String>> suppliersMap = new HashMap<>();
        suppliersMap.put("supplier1", Arrays.asList("item1", "item2"));
        suppliersMap.put("supplier2", Arrays.asList("item3", "item4"));
        MinSupplierFinder finder = new MinSupplierFinder(shortestSelectedSubsets, suppliersMap);


        List<List<String>> result = finder.minAmountOfSuppliers();


        assertEquals(Arrays.asList(Arrays.asList("supplier1", "supplier2")), result);
    }
}