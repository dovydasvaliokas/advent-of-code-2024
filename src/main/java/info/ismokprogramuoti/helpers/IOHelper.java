package info.ismokprogramuoti.helpers;

import info.ismokprogramuoti.exceptions.InputFileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

}
