package info.ismokprogramuoti.days;

import info.ismokprogramuoti.helpers.CollectionsHelper;
import info.ismokprogramuoti.helpers.IOHelper;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day5 {
    public static final Logger log = Logger.getLogger(Day5.class.getName());

    public static final String FILE_NAME = "Day5/Day5Input.txt";
    public static final String EXAMPLE_FILE_NAME = "Day5/Example.txt";

    private Day5() {
    }

    public static void main() {
        List<Pair<Integer, Integer>> rules = new ArrayList<>();
        List<List<Integer>> printingUpdates = new ArrayList<>();
        List<List<Integer>> filteredOrders;
        List<List<Integer>> incorrectOrders = new ArrayList<>();
        double sum;

        // Example

        IOHelper.readPrintingOrdersAndPrintingSequence(EXAMPLE_FILE_NAME, rules, printingUpdates);
        filteredOrders = filterCorrectOrders(rules, printingUpdates);
        sum = CollectionsHelper.sumMiddleNumbersOfLists(filteredOrders);
        log.log(Level.INFO, "Example sum: {0}", sum);

        // Task 1

        printingUpdates = new ArrayList<>();
        rules = new ArrayList<>();

        IOHelper.readPrintingOrdersAndPrintingSequence(FILE_NAME, rules, printingUpdates);
        filteredOrders = filterCorrectOrders(rules, printingUpdates, incorrectOrders);
        sum = CollectionsHelper.sumMiddleNumbersOfLists(filteredOrders);
        log.log(Level.INFO, "Actual task sum: {0}", sum);

        // Part 2
        fixIncorrectOrders(rules, incorrectOrders);
        sum = CollectionsHelper.sumMiddleNumbersOfLists(incorrectOrders);
        log.log(Level.INFO, "Part 2 task sum: {0}", sum);

    }

    public static List<List<Integer>> filterCorrectOrders(List<Pair<Integer, Integer>> rules, List<List<Integer>> printingUpdates) {
        return filterCorrectOrders(rules, printingUpdates, new ArrayList<>());
    }

    public static List<List<Integer>> filterCorrectOrders(List<Pair<Integer, Integer>> rules, List<List<Integer>> printingUpdates, List<List<Integer>> incorrectOrders) {
        List<List<Integer>> filteredOrders = new ArrayList<>();

        for (List<Integer> printingUpdate : printingUpdates) {
            List<Integer> alreadyPrinted = new ArrayList<>();

            if (isPrintingOrderCorrect(rules, printingUpdate, alreadyPrinted)) {
                filteredOrders.add(printingUpdate);
            } else {
                incorrectOrders.add(printingUpdate);
            }
        }

        return filteredOrders;
    }

    public static boolean isPrintingOrderCorrect(List<Pair<Integer, Integer>> rules, List<Integer> printingUpdate, List<Integer> alreadyPrinted) {
        boolean correct = true;

        for (Integer order : printingUpdate) {
            for (Pair<Integer, Integer> rule : rules) {
                if (rule.getLeft().equals(order) && alreadyPrinted.contains(rule.getRight())) {
                    return false;
                }
            }
            alreadyPrinted.add(order);
        }

        return correct;
    }

    public static void fixIncorrectOrders(List<Pair<Integer, Integer>> rules, List<List<Integer>> incorrectOrders) {
        for (List<Integer> incorrectOrder : incorrectOrders) {
            fixIncorrectOrder(rules, incorrectOrder);
        }

    }

    public static void fixIncorrectOrder(List<Pair<Integer, Integer>> rules, List<Integer> incorrectOrder) {
        for (int i = 0; i < incorrectOrder.size(); i++) {
            for (int j = i + 1; j < incorrectOrder.size(); j++) {
                for (Pair<Integer, Integer> rule : rules) {
                    if (Objects.equals(incorrectOrder.get(i), rule.getRight()) && Objects.equals(incorrectOrder.get(j), rule.getLeft())) {
                        Collections.swap(incorrectOrder, i, j);
                        j = i + 1;
                    }
                }
            }
        }
    }
}
