package com.basket.productsDivision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierRankingCreator {
    private final Map<String, List<String>> suppliersMap;
    private final Map<String, Integer> rankingOfSupplier = new HashMap<>();

    public SupplierRankingCreator(Map<String, List<String>> suppliersMap) {
        this.suppliersMap = suppliersMap;
    }
    public Map<String, Integer> createRankingOfSupplier(int maxValueOfRanking) {
        for(Map.Entry<String, List<String>> entry : suppliersMap.entrySet()){
            rankingOfSupplier.put(entry.getKey(), entry.getValue().size());
            if(maxValueOfRanking < entry.getValue().size()) maxValueOfRanking = entry.getValue().size();
        }
        return rankingOfSupplier;
    }

}
