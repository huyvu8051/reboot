package io.huyvu.reboot.backend.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FulltextSearchUtils {
    public static String getFulltextIndex(String input, String... inputVar) {
        String combineInput = mergeInput(input, inputVar);
        String[] sArr = splitFulltext(combineInput);
        StringBuilder rs = new StringBuilder();
        for (String s : sArr) {
            rs.append(s.trim()).append(" ");
        }
        return rs.toString().toLowerCase();
    }

    public static String getSearchCommand(String input, String... inputVar) {
        String combineInput = mergeInput(input, inputVar);
        String[] sArr = splitFulltext(combineInput);
        StringBuilder rs = new StringBuilder("*");
        for (String s : sArr) {
            rs.append(s.trim()).append("*");
        }
        return rs.toString().toLowerCase();
    }

    private static String mergeInput(String input, String[] inputVar) {
        StringBuilder sb = new StringBuilder(input);
        sb.append(" ");
        for (String s : inputVar) {
            sb.append(s);
        }
        var combindInput = sb.toString();
        return combindInput;
    }

    private static String[] splitFulltext(String input) {
        var extractNumbersAndChars = extractNumbersAndChars(input);
        var removeAccents = removeAccents(extractNumbersAndChars);
        var sArr = splitString(extractNumbersAndChars + " " + removeAccents);
        return sArr;
    }


    private static String[] splitString(String input) {
        return input.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)|\\s+");
    }

    private static String extractNumbersAndChars(String input) {
        return input.replaceAll("[\\+\\-\\~\\*\\@\\(\\)\\[\\]\\.\\,]", " ");
    }

    /**
     * for Vietnamese accent.
     *
     * @param input
     * @return
     */
    private static String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }
}
