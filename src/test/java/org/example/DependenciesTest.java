package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DependenciesTest {

    private Dependencies dependencies = new Dependencies();

    @Test
    void test_findDependencies() {
        //Arrange
        Map<String, List<String>> items = new HashMap<>();
        items.put("A", List.of("B", "C"));
        items.put("B", List.of("C", "E"));
        items.put("C", List.of("G"));
        items.put("D", List.of("A", "F"));
        items.put("E", List.of("F"));
        items.put("F", List.of("H"));


        Map<String, Set<String>> expectedResult = new HashMap<>();
        expectedResult.put("A", Set.of("B", "C", "E", "F", "G", "H"));
        expectedResult.put("B", Set.of("C", "E", "F", "G", "H"));
        expectedResult.put("C", Set.of("G"));
        expectedResult.put("D", Set.of("A", "B", "C", "E", "F", "G", "H"));
        expectedResult.put("E", Set.of("F", "H"));
        expectedResult.put("F", Set.of("H"));

        //Act
        Map<String, Set<String>> result = dependencies.findDependencies(items);

        //Assert
        assertEquals(expectedResult.size(), result.size());
        assertEquals(expectedResult, result);
    }

}