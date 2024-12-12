package info.ismokprogramuoti.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CrosswordPuzzleHelper {
    private CrosswordPuzzleHelper() {
    }

    public static int findWordEveryWayOccurenceCountsInPuzzle(List<String> crosswordPuzzleList, String word) {
        int count = 0;
        int currentLetterIndex = 0;
        for (int i = 0; i < crosswordPuzzleList.size(); i++) {
            for (int j = 0; j < crosswordPuzzleList.get(i).length(); j++) {
                List<DirectionToCheck> directionsToCheck = new ArrayList<>();
                directionsToCheck.add(new DirectionToCheck(i, j, 0));
                Iterator<DirectionToCheck> iterator = directionsToCheck.iterator();

                while (iterator.hasNext()) {
                    DirectionToCheck direction = iterator.next();

                    if (checkLetterTopLeftDiagonal(crosswordPuzzleList, word.charAt(direction.currentLetterIndex), i, j)) {
                        directionsToCheck.add(new DirectionToCheck(i - 1, j - 1, direction.currentLetterIndex + 1));
                    }
                }

            }
        }
    }

    // when the whole word has to be in one direction
    public static int findWordOneDirectionOccurenceCountsInPuzzle(List<String> crosswordPuzzleList, String word) {
        int count = 0;
        int currentLetterIndex = 0;

        for (int i = 0; i < crosswordPuzzleList.size(); i++) {
            for (int j = 0; j < crosswordPuzzleList.get(0).length(); j++) {
                if (word.charAt(currentLetterIndex) == crosswordPuzzleList.get(i).charAt(j)) {
                    if (checkWordTopLeftDiagonal(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkWordTop(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkWordTopRightDiagonal(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkWordRight(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkWordBottomRightDiagonal(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkWordBottom(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkWordBottomLeftDiagonal(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                    if (checkWordLeft(crosswordPuzzleList, word, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static boolean checkWordTopLeftDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
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

    public static boolean checkWordTop(List<String> crosswordPuzzleList, String word, int i, int j) {
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

    public static boolean checkWordTopRightDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
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

    public static boolean checkWordRight(List<String> crosswordPuzzleList, String word, int i, int j) {
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

    public static boolean checkWordBottomRightDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
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

    public static boolean checkWordBottom(List<String> crosswordPuzzleList, String word, int i, int j) {
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

    public static boolean checkWordBottomLeftDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
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

    public static boolean checkWordLeft(List<String> crosswordPuzzleList, String word, int i, int j) {
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

    public static boolean checkLetterTopLeftDiagonal(List<String> crosswordPuzzleList, char c, int i, int j) {
        if (i - 1 < 0 || j - 1 < 0) {
            return false;
        }

        return crosswordPuzzleList.get(i - 1).charAt(j - 1) == c;
    }

    public static boolean checkLetterTop(List<String> crosswordPuzzleList, char c, int i, int j) {
        if (i - 1 < 0) {
            return false;
        }

        return crosswordPuzzleList.get(i - 1).charAt(j) == c;
    }

    public static boolean checkLetterTopRightDiagonal(List<String> crosswordPuzzleList, char c, int i, int j) {
        if (i - 1 < 0 || j + 1 > crosswordPuzzleList.get(i).length() - 1) {
            return false;
        }

        return crosswordPuzzleList.get(i - 1).charAt(j + 1) == c;
    }

    public static boolean checkLetterRight(List<String> crosswordPuzzleList, char c, int i, int j) {
        if (j + 1 > crosswordPuzzleList.get(i).length() - 1) {
            return false;
        }

        return crosswordPuzzleList.get(i).charAt(j + 1) == c;
    }

    public static boolean checkLetterBottomRightDiagonal(List<String> crosswordPuzzleList, char c, int i, int j) {
        if (i + 1 > crosswordPuzzleList.size() - 1 || j + 1 > crosswordPuzzleList.get(i).length() - 1) {
            return false;
        }

        return crosswordPuzzleList.get(i + 1).charAt(j + 1) == c;
    }

    public static boolean checkLetterBottom(List<String> crosswordPuzzleList, char c, int i, int j) {
        if (i + 1 > crosswordPuzzleList.size() - 1) {
            return false;
        }

        return crosswordPuzzleList.get(i + 1).charAt(j) == c;
    }

    public static boolean checkLetterBottomLeftDiagonal(List<String> crosswordPuzzleList, char c, int i, int j) {
        if (i + 1 > crosswordPuzzleList.size() - 1 || j - 1 < 0) {
            return false;
        }

        return crosswordPuzzleList.get(i + 1).charAt(j - 1) == c;
    }

    public static boolean checkLetterLeft(List<String> crosswordPuzzleList, char c, int i, int j) {
        if (j - 1 < 0) {
            return false;
        }

        return crosswordPuzzleList.get(i).charAt(j - 1) == c;
    }

    static class DirectionToCheck{
        int i;
        int j;
        int currentLetterIndex;

        public DirectionToCheck() {
        }

        public DirectionToCheck(int i, int j, int currentLetterIndex) {
            this.i = i;
            this.j = j;
            this.currentLetterIndex = currentLetterIndex;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }

        public int getCurrentLetterIndex() {
            return currentLetterIndex;
        }

        public void setCurrentLetterIndex(int currentLetterIndex) {
            this.currentLetterIndex = currentLetterIndex;
        }
    }
}
