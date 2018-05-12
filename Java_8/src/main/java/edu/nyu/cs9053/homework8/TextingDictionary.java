package edu.nyu.cs9053.homework8;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TextingDictionary {

    private final Map<String, List<String>> dictionary;

    public TextingDictionary() {
        this.dictionary = new HashMap<>();
    }

    public void insert(String word) {
        String matchingKey = wordToNum(word);
        if (dictionary.containsKey(matchingKey)) {
            List<String> matchingValues = dictionary.get(matchingKey);
            if (!checkWordExist(matchingValues, word)) {
                dictionary.get(matchingKey).add(word);
            }
        } else {
            List<String> newAddingDict = new LinkedList<>();
            newAddingDict.add(word);
            dictionary.put(matchingKey, newAddingDict);
        }
    }

    public List<String> search(List<ValidTextKeyPress> prefixes) {
        List<String> result = new LinkedList<>();
        if (prefixes == null || prefixes.isEmpty()) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        for (ValidTextKeyPress prefix : prefixes) {
            sb.append(prefix.getValidTextPress());
        }
        String matchingKey = sb.toString();
        for (String key : dictionary.keySet()) {
            if (key.startsWith(matchingKey)) {
                List<String> matchingValues = dictionary.get(key);
                result.addAll(matchingValues);
            }
        }
        return result;
    }

    private boolean isWordValid(String word) {
        if (word == null || word.length() <= 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (!((word.charAt(i) >= 'a' && word.charAt(i) <= 'z') || (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z'))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkWordExist(List<String> listToCheck, String wordToCheck) {
        return listToCheck.stream().anyMatch(s -> String.CASE_INSENSITIVE_ORDER.compare(s, wordToCheck) == 0);
    }

    private String wordToNum(String word) {
        if (!isWordValid(word)) {
            throw new IllegalArgumentException("Input is not valid! Word cannot be null and empty, and it should only contains english letters.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            switch (word.charAt(i)) {
                case 'a':
                case 'b':
                case 'c':
                case 'A':
                case 'B':
                case 'C':
                    sb.append('2');
                    break;
                case 'd':
                case 'e':
                case 'f':
                case 'D':
                case 'E':
                case 'F':
                    sb.append('3');
                    break;
                case 'g':
                case 'h':
                case 'i':
                case 'G':
                case 'H':
                case 'I':
                    sb.append('4');
                    break;
                case 'j':
                case 'k':
                case 'l':
                case 'J':
                case 'K':
                case 'L':
                    sb.append('5');
                    break;
                case 'm':
                case 'n':
                case 'o':
                case 'M':
                case 'N':
                case 'O':
                    sb.append('6');
                    break;
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                    sb.append('7');
                    break;
                case 't':
                case 'u':
                case 'v':
                case 'T':
                case 'U':
                case 'V':
                    sb.append('8');
                    break;
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    sb.append('9');
                    break;
                default:
                    throw new AssertionError("Convert to num assertion error");
            }
        }
        return sb.toString();
    }
}