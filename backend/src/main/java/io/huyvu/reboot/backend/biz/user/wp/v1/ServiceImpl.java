
package io.huyvu.reboot.backend.biz.user.wp.v1;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceImpl implements Service {
    private final Repository wpRepo;

    @Transactional
    @Override
    public WpDetails create(String wpNm, long adminId) {
        long l = wpRepo.insertWp(wpNm);
        long wpId = wpRepo.selectLastInsertId();
        wpRepo.insertWpMem(wpId, adminId, true);
        return wpRepo.selectWpDetails(wpId, adminId).orElseThrow();
    }

    @Override
    public List<ListWpItem> getList(long userId) {
        return wpRepo.selectWpItem(userId);
    }

    @Override
    public WpDetails getDetails(long wpId, long id) {
        return wpRepo.selectWpDetails(wpId, id).orElseThrow();
    }

    @Override
    public List<UserAccount> searchMembers(String keyword) {
        if (keyword.isBlank() || keyword.isEmpty()) return new ArrayList<>();

        String[] words = keyword.split("\\s+");
        StringBuilder outputBuilder = new StringBuilder("*");
        for (String word : words) {
            var ra = removeAccents(word);
            outputBuilder.append(word).append("*");

            var variations = generateAccentVariations(ra);
            for (String variation : variations) {
                outputBuilder.append(variation).append("*");
            }

        }
        var trim = outputBuilder.toString().trim();


        return wpRepo.searchMembers(trim);
    }

    public static String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }

    public static List<String> generateAccentVariations(String input) {
        List<String> variations = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            String baseForm = String.valueOf(c);
            String normalized = Normalizer.normalize(baseForm, Normalizer.Form.NFD);

            for (int j = 0; j < normalized.length(); j++) {
                char currentChar = normalized.charAt(j);
                if (Character.getType(currentChar) == Character.NON_SPACING_MARK) {
                    String variation = baseForm + currentChar;
                    variations.add(variation);
                }
            }
        }

        return variations;
    }
}
