package org.example;

import java.util.*;

public class MinimumSupplierFinder implements CoverBasket {
    private final List<String> universe;
    private final Map<String, List<String>> suppliersMap;

    public MinimumSupplierFinder(List<String> universe, Map<String, List<String>> suppliersMap) {
        this.universe = universe;
        this.suppliersMap = suppliersMap;
    }

    @Override
    public List<List<String>> split() {
//        Stworzeenie listy zawierającej podzbiory produktów, które mogą być dostarczone przez jednego dostawcę,
//        aby następnie znaleźć minimalną ilość podzbiorów produktów, potrzebnych do pokrycia wszystkich produktów z koszyka.
        List<Set<String>> subsets = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : suppliersMap.entrySet())
            subsets.add(new HashSet<>(entry.getValue()));



//        Listy zawierają najmniejszą z możliwych ilości podzbiorów produktów potrzebnych do pokrycia koszyka.
        Set<String> universeOfItems = new HashSet<>(universe);
        ShortestSetCover shortestSetCover = new ShortestSetCover(subsets, universeOfItems);
        Set<List<Set<String>>> shortestSelectedSubsets = shortestSetCover.findShortestSetCovers();

        MinSupplierFinder minSupplierFinder = new MinSupplierFinder(shortestSelectedSubsets, suppliersMap);
        return minSupplierFinder.minAmountOfSuppliers();


    }


}
