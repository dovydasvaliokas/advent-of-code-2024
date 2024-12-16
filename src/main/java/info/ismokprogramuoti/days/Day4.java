package info.ismokprogramuoti.days;

import info.ismokprogramuoti.helpers.CrosswordPuzzleHelper;
import info.ismokprogramuoti.helpers.IOHelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day4 {
    public static final Logger log = Logger.getLogger(Day4.class.getName());

    public static final String FILE_NAME = "Day4/Day4Input.txt";
    public static final String EXAMPLE_FILE_NAME = "Day4/Example.txt";
    public static final String SEARCHABLE_WORD = "XMAS";
    public static final String PART_TWO_SEARCHABLE_WORD = "MAS";

    private Day4() {
    }

    public static void main() {
        List<String> crosswordPuzzleList = IOHelper.readStringList(FILE_NAME);
        List<String> exampleList = IOHelper.readStringList(EXAMPLE_FILE_NAME);

        log.log(Level.INFO, "Example count: {0}", CrosswordPuzzleHelper.findWordOneDirectionOccurenceCountsInPuzzle(exampleList, SEARCHABLE_WORD));
        log.log(Level.INFO, "Count: {0}", CrosswordPuzzleHelper.findWordOneDirectionOccurenceCountsInPuzzle(crosswordPuzzleList, SEARCHABLE_WORD));


        // TODO: finish PART 2
        log.log(Level.INFO, "Example count of X-MAS: {0}", CrosswordPuzzleHelper.findXOfWordsOccurenceCountsInPuzzle(exampleList, PART_TWO_SEARCHABLE_WORD));
        log.log(Level.INFO, "Task list count X-MAS: {0}", CrosswordPuzzleHelper.findXOfWordsOccurenceCountsInPuzzle(crosswordPuzzleList, PART_TWO_SEARCHABLE_WORD));

    }
}
