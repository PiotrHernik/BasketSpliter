package com.basket;

import com.basket.basketCover.ShortestSetCover;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShortestSetCoverTest {
    @Test
    public void testFindShortestSetCovers() {
        // Arrange
        List<Set<String>> subsets = new ArrayList<>();
        subsets.add(new HashSet<>(Arrays.asList("item1", "item2")));
        subsets.add(new HashSet<>(Arrays.asList("item3", "item4")));
        subsets.add(new HashSet<>(Arrays.asList("item4", "item5")));
        subsets.add(new HashSet<>(Arrays.asList("item2", "item1", "item5")));
        Set<String> universe = new HashSet<>(Arrays.asList("item1", "item2", "item3", "item4", "item5"));
        ShortestSetCover cover = new ShortestSetCover(subsets, universe);

        // Act
        Set<List<Set<String>>> result = cover.findShortestSetCovers();

        // Assert
        Set<List<Set<String>>> expected1 = new HashSet<>();
        expected1.add(Arrays.asList(new HashSet<>(Arrays.asList("item3", "item4")), new HashSet<>(Arrays.asList("item2", "item1", "item5"))));
        Set<List<Set<String>>> expected2 = new HashSet<>();
        expected2.add(Arrays.asList(new HashSet<>(Arrays.asList("item2", "item1", "item5")), new HashSet<>(Arrays.asList("item3", "item4"))));

        Set<Set<List<Set<String>>>> expectedResults = new HashSet<>();
        expectedResults.add(expected1);
        expectedResults.add(expected2);
        assertTrue(expectedResults.contains(result));
    }
}