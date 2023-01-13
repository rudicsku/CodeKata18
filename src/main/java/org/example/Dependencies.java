package org.example;

import java.util.*;

public class Dependencies {
    public Map<String, Set<String>> findDependencies(Map<String, List<String>> items) {

        //Converting the values of items from List to Set
        Map<String, Set<String>> result = new HashMap<>();
        for (Map.Entry<String, List<String>> entrySet : items.entrySet()) {
            result.put(entrySet.getKey(), new HashSet<>(entrySet.getValue()));
        }

        //Iterating over the map
        boolean until = true;
        while (until) {

            for (String key : result.keySet()) {
                for (String values : result.get(key)) {
                    if (result.containsKey(values)) {
                        Set<String> temp = new HashSet<>(result.get(key));

                        if (temp.containsAll(result.get(values))) {
                            until = false;
                        }

                        temp.addAll(result.get(values));
                        result.put(key, temp);
                    }
                }
            }
        }
        return result;
    }
}
