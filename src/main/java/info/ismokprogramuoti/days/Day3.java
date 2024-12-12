package info.ismokprogramuoti.days;

import info.ismokprogramuoti.helpers.IOHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static final Logger log = Logger.getLogger(Day3.class.getName());

    public static final String FILE_NAME = "Day3/Day3Input.txt";

    private Day3() {
    }

    public static void main() {
        // Task 1
        String command = IOHelper.readAllFileToString(FILE_NAME);
        String multRegex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern multPattern = Pattern.compile(multRegex);
        Matcher multMatcher = multPattern.matcher(command);
        int product = 0;

        while (multMatcher.find()) {
            product += multiplyCommand(multMatcher);
        }

        log.log(Level.INFO, "Product: {0}", product);

        // Task 2
        multMatcher.reset();

        String doDontRegex = "(do\\(\\)|don't\\(\\))";
        String doRegex = "do\\(\\)";

        String combinedMultAndDoDontRegex = "(" + multRegex + "|" + doDontRegex + ")";
        Pattern combinedMultAndDoDontPattern = Pattern.compile(combinedMultAndDoDontRegex);
        Matcher combinedMultAndDoDontMatcher = combinedMultAndDoDontPattern.matcher(command);

        boolean doMultiply = true;
        product = 0;

        while (combinedMultAndDoDontMatcher.find()) {
            if (combinedMultAndDoDontMatcher.group().matches(multRegex)) {
                if (doMultiply) {
                    product += multiplyCommand(combinedMultAndDoDontMatcher);
                }
            } else {
                doMultiply = combinedMultAndDoDontMatcher.group().matches(doRegex);
            }
        }

        log.log(Level.INFO, "Product: {0}", product);
    }

    public static int multiplyCommand(Matcher multMatcher) {
        int product = 0;

        String numberRegex = "\\d+";
        Pattern numberPattern = Pattern.compile(numberRegex);
        Matcher numberMatcher = numberPattern.matcher(multMatcher.group());

        List<Integer> productNumbers = new ArrayList<>();
        while (numberMatcher.find()) {
            productNumbers.add(Integer.parseInt(numberMatcher.group()));
        }

        product += productNumbers.stream().reduce(1, (a, b) -> a * b);
        return product;
    }
}
