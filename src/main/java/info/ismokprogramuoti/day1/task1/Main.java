package info.ismokprogramuoti.day1.task1;

import info.ismokprogramuoti.helpers.IOHelper;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String TASK1_INPUT = "Day1/Day1Task1Input.txt";
    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Task 1
        List<List<Integer>> locationIdLists = IOHelper.readAdjacentLists(TASK1_INPUT)
                .stream()
                .map(innerList -> innerList
                        .stream()
                        .map(Integer::parseInt)
                        .toList())
                .toList();
        List<Integer> leftList = locationIdLists.get(0);
        List<Integer> rightList = locationIdLists.get(1);

        leftList.sort(Integer::compareTo);
        rightList.sort(Integer::compareTo);
        int diff = 0;
        for (int i = 0; i < leftList.size(); i++) {
            diff += Math.abs(leftList.get(i) - rightList.get(i));
        }

        logger.log(Level.INFO, "diff = {0}", diff);


        // Task 2
        int similarityScore = 0;
        for (Integer locationId : leftList) {
            similarityScore += locationId * Collections.frequency(rightList, locationId);
        }

        logger.log(Level.INFO, "similarityScore = {0}", similarityScore);
    }

}
