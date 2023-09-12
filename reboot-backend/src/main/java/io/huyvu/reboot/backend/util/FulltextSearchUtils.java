package io.huyvu.reboot.backend.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FulltextSearchUtils {
    public static String getFulltextIndex(String input, String... inputVar) {
        var combineInput = mergeInput(input, inputVar);
        var sArr = splitFulltext(combineInput);
        var rs = new StringBuilder();
        for (var s : sArr) {
            rs.append(s.trim()).append(" ");
        }
        return rs.toString().toLowerCase();
    }

    public static String getSearchCommand(String input, String... inputVar) {
        var combineInput = mergeInput(input, inputVar);
        var sArr = splitFulltext(combineInput);
        var rs = new StringBuilder();
        for (var s : sArr) {
            if(s.trim() != ""){
                rs.append("*").append(s.trim()).append("* ");
            }

        }
        return rs.toString().toLowerCase();
    }

    private static String mergeInput(String input, String[] inputVar) {
        var sb = new StringBuilder(input);
        sb.append(" ");
        for (var s : inputVar) {
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
        var normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        var pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }
}
