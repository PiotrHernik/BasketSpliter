package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuppliersMapCreator {
    private final List<String> items;
    private final Map<String, List<String>> config;
    private final Map<String, List<String>> suppliersMap = new HashMap<>();

    public SuppliersMapCreator(List<String> items, Map<String, List<String>> config) {
        this.items = items;
        this.config = config;
    }
    public Map<String, List<String>> createSuppliersMap() {

        for(String item : items){

            List<String> suppliers = config.get(item);

            for(String supplier : suppliers){
                if(suppliersMap.containsKey(supplier)){

                    suppliersMap.get(supplier).add(item);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(item);
//                    System.out.println(suppliersMap);
                    suppliersMap.put(supplier, list);
                }
            }

        }

//        System.out.println(suppliersMap);
        return suppliersMap;
    }


}
