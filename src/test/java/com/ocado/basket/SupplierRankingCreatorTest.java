package com.ocado.basket;

import com.ocado.basket.productsDivision.SupplierRankingCreator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SupplierRankingCreatorTest {
    @Test
    public void testCreateRankingOfSupplier() {
        // Arrange
        Map<String, List<String>> suppliersMap = new HashMap<>();
        suppliersMap.put("supplier1", Arrays.asList("item1", "item2"));
        suppliersMap.put("supplier2", Arrays.asList("item3", "item4", "item5"));
        SupplierRankingCreator creator = new SupplierRankingCreator(suppliersMap);

        // Act
        Map<String, Integer> result = creator.createRankingOfSupplier(0);

        // Assert
        Map<String, Integer> expected = new HashMap<>();
        expected.put("supplier1", 2);
        expected.put("supplier2", 3);
        assertEquals(expected, result);
    }
}