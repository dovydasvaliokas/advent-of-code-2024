package info.ismokprogramuoti.days;

import info.ismokprogramuoti.helpers.CollectionsHelper;
import info.ismokprogramuoti.helpers.IOHelper;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day1 {
    public static final String TASK1_INPUT = "Day1/Day1Task1Input.txt";
    public static final Logger logger = Logger.getLogger(Day1.class.getName());

    private Day1() {
    }

    public static void main() {
        // Task 1
        List<List<Integer>> locationIdLists = CollectionsHelper.convertAdjListsToInteger(IOHelper.readAdjacentLists(TASK1_INPUT));
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
