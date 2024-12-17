package info.ismokprogramuoti.helpers;

import info.ismokprogramuoti.exceptions.InputFileNotFoundException;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IOHelper {
    public static final String RESOURCE_PATH = "src/main/resources/";

    private IOHelper() {
    }

    public static List<List<String>> readAdjacentLists(String fileName) {
        File file = createFileFromResources(fileName);
        List<List<String>> adjacentLists = new ArrayList<>();

        try (Scanner scanner = new Scanner(file);
             Scanner scannerCountCols = new Scanner(file)) {

            String firstLine = scannerCountCols.nextLine();
            int colCount = firstLine.split("\\s+").length - (firstLine.trim().isEmpty() ? 1 : 0);
            for (int i = 0; i < colCount; i++) {
                adjacentLists.add(new ArrayList<>());
            }

            while (scanner.hasNextLine()) {
                for (int i = 0; i < colCount; i++) {
                    adjacentLists.get(i).add(scanner.next());
                }
            }
        } catch (FileNotFoundException e) {
            throw new InputFileNotFoundException(fileName, e);
        }

        return adjacentLists;
    }

    public static List<String> readStringList(String fileName) {
        File file = createFileFromResources(fileName);
        List<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new InputFileNotFoundException(fileName, e);
        }

        return list;
    }

    public static File createFileFromResources(String fileName) {
        return new File(RESOURCE_PATH + fileName);
    }

    public static String readAllFileToString(String fileName) {
        File file = createFileFromResources(fileName);
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("\\A");
            return scanner.next();
        } catch (FileNotFoundException e) {
            throw new InputFileNotFoundException(fileName, e);
        }
    }

    // More concrete IO method for: https://adventofcode.com/2024/day/5 part 1
    public static void readPrintingOrdersAndPrintingSequence(String fileName, List<Pair<Integer, Integer>> rules, List<List<Integer>> printingUpdates) {
        File file = createFileFromResources(fileName);

        try (Scanner scanner = new Scanner(file)) {
            String s;
            do {
                s = scanner.nextLine();

                if (s.contains("|")) {
                    String[] rule = s.split(("\\|"));
                    rules.add(Pair.of(Integer.parseInt(rule[0]), Integer.parseInt(rule[1])));
                }
            }
            while (scanner.hasNextLine() && s.contains("|"));

            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
                String[] updates = s.split(",");
                List<Integer> updateList = Arrays.stream(updates).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

                printingUpdates.add(updateList);
            }
        } catch (FileNotFoundException e) {
            throw new InputFileNotFoundException(fileName, e);
        }
    }

}
