package com.ocado.basket;

import java.util.*;

public class MinSupplierFinder {
    private Set<List<Set<String>>> shortestSelectedSubsets;
    private Map<String, List<String>> suppliersMap;

    public MinSupplierFinder(Set<List<Set<String>>> shortestSelectedSubsets, Map<String, List<String>> suppliersMap) {
        this.shortestSelectedSubsets = shortestSelectedSubsets;
        this.suppliersMap = suppliersMap;
    }

    public List<List<String>> minAmountOfSuppliers() {

        List<List<String>> listOfMinSuppliers = new ArrayList<>();
        for (List<Set<String>> selectedSubsets : shortestSelectedSubsets) {
            List<String> suppliersForSubsets = new ArrayList<>();
            for (Set<String> subset : selectedSubsets) {
                for (Map.Entry<String, List<String>> entry : suppliersMap.entrySet()) {
                    if (new HashSet<>(entry.getValue()).equals(subset)) {
                        suppliersForSubsets.add(entry.getKey());
                        break;
                    }
                }
            }
            listOfMinSuppliers.add(suppliersForSubsets);


        }
        return listOfMinSuppliers;
    }
}
