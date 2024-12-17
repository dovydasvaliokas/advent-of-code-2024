package info.ismokprogramuoti.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsHelper {
    private CollectionsHelper() {
    }

    public static List<List<Integer>> convertAdjListsToInteger(List<List<String>> adjList) {
        return adjList
                .stream()
                .map(innerList -> innerList
                        .stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<Integer> convertLineToIntegerList(String line) {
        return Arrays.stream(line.trim().split(" "))
                .mapToInt(Integer::parseInt).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static <T extends Number> double sumMiddleNumbersOfLists(List<List<T>> list) {
        double sum = 0;

        for (List<? extends Number> integers : list) {
            sum += integers.get(integers.size() / 2).doubleValue();
        }

        return sum;
    }
}
