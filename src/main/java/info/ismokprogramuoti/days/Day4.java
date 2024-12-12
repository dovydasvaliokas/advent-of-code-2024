package info.ismokprogramuoti.days;

import info.ismokprogramuoti.helpers.CrosswordPuzzleHelper;
import info.ismokprogramuoti.helpers.IOHelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day4 {
    public static final Logger log = Logger.getLogger(Day4.class.getName());

    public static final String FILE_NAME = "Day4/Day4Input.txt";
    public static final String SEARCHABLE_WORD = "XMAS";

    private Day4() {
    }

    public static void main() {
        List<String> crosswordPuzzleList = IOHelper.readStringList(FILE_NAME);

        log.log(Level.INFO, "Count: {0}", CrosswordPuzzleHelper.findWordOneDirectionOccurenceCountsInPuzzle(crosswordPuzzleList, SEARCHABLE_WORD));
    }
}
