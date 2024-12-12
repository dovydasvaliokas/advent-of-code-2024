package info.ismokprogramuoti.days;

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

        log.log(Level.INFO, "Count: {0}", findWordOccurenceCountsInPuzzle(crosswordPuzzleList, SEARCHABLE_WORD));
    }

    public static int findWordOccurenceCountsInPuzzle(List<String> crosswordPuzzleList, String word) {
        int count = 0;
        int currentLetterIndex = 0;

        for (int i = 0; i < crosswordPuzzleList.size(); i++) {
            for (int j = 0; j < crosswordPuzzleList.get(0).length(); j++) {
                if (word.charAt(currentLetterIndex) == crosswordPuzzleList.get(i).charAt(j)) {
                    if (checkTopLeftDiagonal(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkTop(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkTopRightDiagonal(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkRight(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkBottomRightDiagonal(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkBottom(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkBottomLeftDiagonal(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkLeft(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static boolean checkTopLeftDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
        StringBuilder puzzleWord = new StringBuilder(String.valueOf(word.charAt(0)));

        if (i - word.length() < 0 || j - word.length() < 0) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {       // starting from 1, since 0th index is already checked in the main loop
            if (word.charAt(k) != crosswordPuzzleList.get(i - k).charAt(j - k)) {
                return false;
            }
            puzzleWord.append(crosswordPuzzleList.get(i - k).charAt(j - k));
        }

//        if (!puzzleWord.toString().equals(word)) {
//            return false;
//        }

        return true;
    }

    public static boolean checkTop(List<String> crosswordPuzzleList, String word, int i, int j) {
        StringBuilder puzzleWord = new StringBuilder(String.valueOf(word.charAt(0)));

        if (i - word.length() < 0) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i - 1).charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkTopRightDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
        StringBuilder puzzleWord = new StringBuilder(String.valueOf(word.charAt(0)));

        if (i - word.length() < 0 || j + word.length() > crosswordPuzzleList.getFirst().length() - 1) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i - k).charAt(j + k)) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkRight (List<String> crosswordPuzzleList, String word, int i, int j) {
        StringBuilder puzzleWord = new StringBuilder(String.valueOf(word.charAt(0)));

        if (j + word.length() > crosswordPuzzleList.getFirst().length() - 1) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i).charAt(j + k)) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkBottomRightDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
        StringBuilder puzzleWord = new StringBuilder(String.valueOf(word.charAt(0)));

        if (i + word.length() > crosswordPuzzleList.size() - 1 || j + word.length() > crosswordPuzzleList.getFirst().length() - 1) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i + k).charAt(j + k)) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkBottom(List<String> crosswordPuzzleList, String word, int i, int j) {
        StringBuilder puzzleWord = new StringBuilder(String.valueOf(word.charAt(0)));

        if (i + word.length() > crosswordPuzzleList.size() - 1) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i + k).charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkBottomLeftDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
        StringBuilder puzzleWord = new StringBuilder(String.valueOf(word.charAt(0)));

        if (i + word.length() > crosswordPuzzleList.size() - 1 || j - word.length() < 0) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i + k).charAt(j - k)) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkLeft(List<String> crosswordPuzzleList, String word, int i, int j) {
        StringBuilder puzzleWord = new StringBuilder(String.valueOf(word.charAt(0)));

        if (j - word.length() < 0) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i).charAt(j - k)) {
                return false;
            }
        }

        return true;
    }
}
