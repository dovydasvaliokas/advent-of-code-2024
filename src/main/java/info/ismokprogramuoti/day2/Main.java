package info.ismokprogramuoti.day2;

import info.ismokprogramuoti.helpers.CollectionsHelper;
import info.ismokprogramuoti.helpers.IOHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static final String FILE_NAME = "Day2/Day2Input.txt";
    public static final int MIN_DIFF = 1;
    public static final int MAX_DIFF = 3;

    private Main() {
    }

    public static void main() {
        List<String> reports = IOHelper.readStringList(FILE_NAME);


        int countSafe = 0;
        for (String levelsString : reports) {
            List<Integer> levels = CollectionsHelper.convertLineToIntegerList(levelsString);
            boolean isIncreasing = levels.get(0) < levels.get(1);

            if (isReportSafe(levels, isIncreasing)) {
                countSafe++;
            }
        }

        logger.log(Level.INFO, "countSafe: {0}", countSafe);


        // Part 2
        countSafe = 0;
        List<Boolean> isSafeList = new ArrayList<>();
        for (String levelsString : reports) {
            List<Integer> levels = CollectionsHelper.convertLineToIntegerList(levelsString);
            boolean isIncreasing = levels.get(0) < levels.get(1);
            filterOutOneError(levels, isIncreasing);

            isSafeList.add(isReportSafe(levels, isIncreasing));
            if (isReportSafe(levels, isIncreasing)) {
                countSafe++;
            }
        }

        for (int i = 0; i < isSafeList.size(); i++) {
            if (Boolean.FALSE.equals(isSafeList.get(i))) {
                List<Integer> levels = CollectionsHelper.convertLineToIntegerList(reports.get(i));
                levels = levels.reversed();
                boolean isIncreasing = levels.get(0) < levels.get(1);
                filterOutOneError(levels, isIncreasing);

                if (isReportSafe(levels, isIncreasing)) {
                    countSafe++;
                }
            }
        }

        logger.log(Level.INFO, "countSafe: {0}", countSafe);
    }


    private static boolean isReportSafe(List<Integer> levels, boolean isIncreasing) {
        boolean isSafe = true;

        for (int i = 0; i < levels.size() - 1; i++) {
            if (areLevelsNotSafe(levels.get(i), levels.get(i + 1), isIncreasing)) {
                return false;
            }
        }

        return isSafe;
    }

    private static boolean areLevelsNotSafe(int lowerLevel, int upperLevel, boolean isIncreasing) {
        boolean isSafe = true;

        if (isIncreasing) {
            if (upperLevel < lowerLevel + MIN_DIFF || upperLevel > lowerLevel + MAX_DIFF) {
                isSafe = false;
            }
        } else {
            if (upperLevel > lowerLevel - MIN_DIFF || upperLevel < lowerLevel - MAX_DIFF) {
                isSafe = false;
            }
        }

        return isSafe;
    }

    private static void filterOutOneError(List<Integer> levels, boolean isIncreasing) {
        if (!isReportSafe(levels, isIncreasing)) {
            for (int i = 0; i < levels.size() - 1; i++) {
                if (areLevelsNotSafe(levels.get(i), levels.get(i + 1), isIncreasing)) {
                    levels.remove(i + 1);
                    return;

                }
            }
        }
    }


}
