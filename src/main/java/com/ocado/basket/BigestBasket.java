package com.ocado.basket;

import java.util.*;

public class BigestBasket implements DivisionProducts{
    private final List<List<String>> listOfMinSuppliers;
    private final Map<String, List<String>> suppliersMap;
    private final int maxValOfRanking;
    private final SupplierRankingCreator supplierRankingCreator;
    public BigestBasket(List<List<String>> listOfMinSuppliers, Map<String, List<String>> suppliersMap, int maxValOfRanking) {
        this.listOfMinSuppliers = listOfMinSuppliers;
        this.suppliersMap = suppliersMap;
        this.maxValOfRanking = maxValOfRanking;
        supplierRankingCreator = new SupplierRankingCreator(suppliersMap);
    }
    @Override
    public Map<String, List<String>> split() {
        Map<String, Integer> rankingOfSupplier = supplierRankingCreator.createRankingOfSupplier(maxValOfRanking);

        Map<String, List<String>> optimalDistribution = new HashMap<>();
        int maxValue2 = 0;
        int whichList = 0;
        int suppliersPosition = 0;

        for (int i = 0; i < listOfMinSuppliers.size() - 1; i++) {
            for (String supplier : listOfMinSuppliers.get(i)) {
                if (rankingOfSupplier.get(supplier) > maxValue2) {
                    maxValue2 = rankingOfSupplier.get(supplier);
                    whichList = i;
                    suppliersPosition++;
                }
                if (maxValue2 == maxValOfRanking) {
                    optimalDistribution.put(supplier, suppliersMap.get(supplier));
                    for (String anotherSupplier : listOfMinSuppliers.get(i)) {
                        if (!anotherSupplier.equals(supplier)) {
                            List<String> list = new ArrayList<>(suppliersMap.get(anotherSupplier));
                            list.removeAll(suppliersMap.get(supplier));
                            optimalDistribution.put(anotherSupplier, list);
                        }
                    }
                    return optimalDistribution;
                }


            }
        }
        optimalDistribution.put(listOfMinSuppliers.get(whichList).get(suppliersPosition),
                suppliersMap.get(listOfMinSuppliers.get(whichList).get(suppliersPosition)));

        List<String> suppliers = new ArrayList<>(listOfMinSuppliers.get(whichList));
        suppliers.sort(Comparator.comparing(rankingOfSupplier::get).reversed());


        for(int i = 0; i < suppliers.size() - 1; i++){
            List<String> list = new ArrayList<>(suppliersMap.get(suppliers.get(i)));
            for(int j = i + 1; j < suppliers.size(); j++){
                suppliersMap.get(suppliers.get(j)).removeAll(list);
            }
        }

        for(String supplier : suppliers){
            optimalDistribution.put(supplier, suppliersMap.get(supplier));
        }


        return optimalDistribution;
    }
}
