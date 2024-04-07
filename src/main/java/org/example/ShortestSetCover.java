package org.example;

import java.util.*;

public class ShortestSetCover {
    private List<Set<String>> subsets;
    private Set<String> universe;

    public ShortestSetCover(List<Set<String>> subsets, Set<String> universe) {
        this.subsets = subsets;
        this.universe = universe;
    }

    public Set<List<Set<String>>> findShortestSetCovers(){
        for (int i = 1; i <= subsets.size(); i++) {
            Set<List<Set<String>>> selectedSubsets = new HashSet<>();
            findSetCoversRecursive(subsets, universe, new ArrayList<>(), i, selectedSubsets);
            if (!selectedSubsets.isEmpty()) {
                return selectedSubsets;
            }
        }
        return null;
    }
    private void findSetCoversRecursive(List<Set<String>> subsets, Set<String> universe,
                                        List<Set<String>> selectedSubsets, int subsetCount,
                                        Set<List<Set<String>>> allSelectedSubsets) {
        // Sprawdź, czy wszystkie elementy uniwersum zostały już pokryte
        if (universe.isEmpty()) {
            if (selectedSubsets.size() == subsetCount) {
                List<Set<String>> sortedSelectedSubsets = new ArrayList<>(selectedSubsets);
                sortedSelectedSubsets.sort(Comparator.comparing(Object::toString));
                allSelectedSubsets.add(sortedSelectedSubsets);
            }
            return;
        }

        // Dla każdego podzbioru, który pokrywa niepokryte elementy uniwersum
        for (Set<String> subset : subsets) {
            // Sprawdź, czy podzbiór pokrywa niepokryte elementy
            Set<String> uncovered = new HashSet<>(universe);
            uncovered.removeAll(subset);

            // Jeśli podzbiór pokrywa niepokryte elementy, zastosuj rekurencję dla pozostałych elementów
            if (uncovered.size() < universe.size()) {
                selectedSubsets.add(subset);
                findSetCoversRecursive(subsets, uncovered, selectedSubsets, subsetCount, allSelectedSubsets);
                selectedSubsets.remove(selectedSubsets.size() - 1);
            }
        }
    }


}
