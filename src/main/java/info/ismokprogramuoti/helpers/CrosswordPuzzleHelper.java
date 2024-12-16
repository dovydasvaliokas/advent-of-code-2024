package info.ismokprogramuoti.helpers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CrosswordPuzzleHelper {
    private CrosswordPuzzleHelper() {
    }

    public static int findWordEveryWayOccurenceCountsInPuzzle(List<String> crosswordPuzzleList, String word) {
        int count = 0;

        for (int i = 0; i < crosswordPuzzleList.size(); i++) {
            for (int j = 0; j < crosswordPuzzleList.get(i).length(); j++) {
                Deque<DirectionToCheck> directionsToCheck = new LinkedList<>();
                directionsToCheck.add(new DirectionToCheck(i, j, 0));

                while (!directionsToCheck.isEmpty()) {
                    DirectionToCheck directionToCheck = directionsToCheck.pop();

                    if (directionToCheck.getCurrentLetterIndex() > 2) {
                        count ++;
                    } else {
                        char currentLetter = word.charAt(directionToCheck.getCurrentLetterIndex() + 1);

                        if (checkLetterTopLeftDiagonal(crosswordPuzzleList, currentLetter, i, j)) {
                            directionsToCheck.push(new DirectionToCheck(i - 1, j - 1, directionToCheck.getCurrentLetterIndex() + 1));
                        }

                        if (checkLetterTop(crosswordPuzzleList, currentLetter, i, j)) {
                            directionsToCheck.push(new DirectionToCheck(i - 1, j, directionToCheck.getCurrentLetterIndex() + 1));
                        }

                        if (checkLetterTopRightDiagonal(crosswordPuzzleList, currentLetter, i, j)) {
                            directionsToCheck.push(new DirectionToCheck(i - 1, j + 1, directionToCheck.getCurrentLetterIndex() + 1));
                        }

                        if (checkLetterRight(crosswordPuzzleList, currentLetter, i, j)) {
                            directionsToCheck.push(new DirectionToCheck(i, j + 1, directionToCheck.getCurrentLetterIndex() + 1));
                        }

                        if (checkLetterBottomRightDiagonal(crosswordPuzzleList, currentLetter, i, j)) {
                            directionsToCheck.push(new DirectionToCheck(i + 1, j + 1, directionToCheck.getCurrentLetterIndex() + 1));
                        }

                        if (checkLetterBottom(crosswordPuzzleList, currentLetter, i, j)) {
                            directionsToCheck.push(new DirectionToCheck(i + 1, j, directionToCheck.getCurrentLetterIndex() + 1));
                        }

                        if (checkLetterBottomLeftDiagonal(crosswordPuzzleList, currentLetter, i, j)) {
                            directionsToCheck.push(new DirectionToCheck(i - 1, j - 1, directionToCheck.getCurrentLetterIndex() + 1));
                        }

                        if (checkLetterLeft(crosswordPuzzleList, currentLetter, i, j)) {
                            directionsToCheck.push(new DirectionToCheck(i, j - 1, directionToCheck.getCurrentLetterIndex() + 1));
                        }
                    }
                }
            }
        }

        return count;
    }

    // when the whole word has to be in one direction
    public static int findWordOneDirectionOccurenceCountsInPuzzle(List<String> crosswordPuzzleList, String word) {
        int count = 0;

        for (int i = 0; i < crosswordPuzzleList.size(); i++) {
            for (int j = 0; j < crosswordPuzzleList.getFirst().length(); j++) {
                if (word.charAt(0) == crosswordPuzzleList.get(i).charAt(j)) {
                    count += checkWordEachDirection(crosswordPuzzleList, word, i, j);
                }
            }
        }

        return count;
    }

    public static int findXOfWordsOccurenceCountsInPuzzle(List<String> crosswordPuzzleList, String word) {
        int count = 0;
        int wordLengthMinusOne = word.length() - 1;
        int wordMiddleLetterIndex = word.length() / 2;
        List<Point> usedMiddleLetters = new ArrayList<>();

        for (int i = 0; i < crosswordPuzzleList.size(); i++) {
            for (int j = 0; j < crosswordPuzzleList.getFirst().length(); j++) {
                if (word.charAt(0) == crosswordPuzzleList.get(i).charAt(j)) {
                    if (checkWordTopLeftDiagonal(crosswordPuzzleList, word, i, j)) {
                        Point middleLetterPoint = new Point(i - wordMiddleLetterIndex, j - wordMiddleLetterIndex);

                        if (checkWordTopRightDiagonal(crosswordPuzzleList, word, i, j - wordLengthMinusOne)
                                && !usedMiddleLetters.contains(middleLetterPoint)) {
                            usedMiddleLetters.add(middleLetterPoint);
                            count++;
                        }

                        if (checkWordBottomLeftDiagonal(crosswordPuzzleList, word, i - wordLengthMinusOne, j)
                                && !usedMiddleLetters.contains(middleLetterPoint)) {
                            usedMiddleLetters.add(middleLetterPoint);
                            count++;
                        }

                    }
                    if (checkWordBottomRightDiagonal(crosswordPuzzleList, word, i, j)) {
                        Point middleLetterPoint = new Point(i + wordMiddleLetterIndex, j + wordMiddleLetterIndex);

                        if (checkWordBottomLeftDiagonal(crosswordPuzzleList, word, i, j + wordLengthMinusOne)
                                && !usedMiddleLetters.contains(middleLetterPoint)) {
                            usedMiddleLetters.add(middleLetterPoint);
                            count++;
                        }

                        if (checkWordTopRightDiagonal(crosswordPuzzleList, word, i + wordLengthMinusOne, j)
                                && !usedMiddleLetters.contains(middleLetterPoint)) {
                            usedMiddleLetters.add(middleLetterPoint);
                            count++;
                        }

                    }
                }
            }
        }

        return count;
    }

    public static boolean checkWordTopLeftDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
        if (i - word.length() < -1 || j - word.length() < -1) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {       // starting from 1, since 0th index is already checked in the main loop
            if (word.charAt(k) != crosswordPuzzleList.get(i - k).charAt(j - k)) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkWordTop(List<String> crosswordPuzzleList, String word, int i, int j) {
        if (i - word.length() < -1) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i - k).charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkWordTopRightDiagonal(List<String> crosswordPuzzleList, String word, int i, int j) {
        if (i - word.length() < -1 || j + word.length() > crosswordPuzzleList.getFirst().length()) {
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
        if (j + word.length() > crosswordPuzzleList.getFirst().length()) {
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
        if (i + word.length() > crosswordPuzzleList.size() || j + word.length() > crosswordPuzzleList.getFirst().length()) {
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
        if (i + word.length() > crosswordPuzzleList.size()) {
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
        if (i + word.length() > crosswordPuzzleList.size() || j - word.length() < -1) {
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
        if (j - word.length() < -1) {
            return false;
        }

        for (int k = 1; k < word.length(); k++) {
            if (word.charAt(k) != crosswordPuzzleList.get(i).charAt(j - k)) {
                return false;
            }
        }

        return true;
    }

    public static int checkWordEachDirection(List<String> crosswordPuzzleList, String word, int i, int j) {
        int count = 0;

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

        return count;
    }

    // Letters (might not be working, since not tested)

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
