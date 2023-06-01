package io.huyvu.reboot.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}, scanBasePackages = {"io.huyvu.mybatix", "io.huyvu.reboot.backend"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RebootApplication {
    private static final Map<Character, List<Character>> availableVietnameseAccents = new HashMap<>();

    static {
        char[] baseCharacters = {'a', 'ă', 'â', 'e', 'ê', 'i', 'o', 'ô', 'ơ', 'u', 'ư', 'y'};
        String[] diacriticalMarks = {"\u0301", "\u0300", "\u0303", "\u0309", "\u0323"};

        for (char baseChar : baseCharacters) {
            for (String diacriticalMark : diacriticalMarks) {
                String character = baseChar + diacriticalMark;
                String normalized = Normalizer.normalize(character, Normalizer.Form.NFC);

                var s1 = removeAccents(normalized);
                List<Character> characters = getAccentCharacters(s1.charAt(0));
                if(characters.size() == 0) characters.add(baseChar);
                characters.add(normalized.charAt(0));
            }
        }

        var dAccents = List.of('d','đ');
        availableVietnameseAccents.put('d', dAccents);

    }

    private static List<Character> getAccentCharacters(char c) {
        var characters = availableVietnameseAccents.get(c);
        if (characters == null) {
            characters = new ArrayList<>();
            availableVietnameseAccents.put(c, characters);
        }
        return characters;
    }

    public static void main(String[] args) {
//        ConfigurableApplicationContext run = new SpringApplicationBuilder(RebootApplication.class)
//                .beanNameGenerator(new FullyQualifiedAnnotationBeanNameGenerator())
//                .run(args);

        String[] words = "Chung ta cua hien tai".split("\\s+");
        StringBuilder outputBuilder = new StringBuilder("*");
        for (String word : words) {
            var ra = removeAccents(word);
            String sb = getStringBuilder(ra);

            outputBuilder.append(sb);

        }


        System.out.println(outputBuilder);


    }

    private static String getStringBuilder(String s) {
        StringBuilder sb = new StringBuilder('*');

        int[] levels = new int[s.toCharArray().length];


        int totalSituation = 1;

        for (char c : s.toCharArray()) {
            var characters = availableVietnameseAccents.get(c);
            if (characters != null) {
                totalSituation *= characters.size();
            }
        }

        int loopCnt = 0;

        while (loopCnt != totalSituation) {
            levels[levels.length - 1]++;


            for (int i = s.toCharArray().length - 1; i >= 0; i--) {
                var characters = availableVietnameseAccents.get(s.charAt(i));
                if (characters == null) {
                    characters = List.of(s.charAt(i));
                    availableVietnameseAccents.put(s.charAt(i), characters);
                }

                if (levels[i] >= characters.size()) {
                    levels[i] = 0;
                    if (i > 0) {
                        levels[i - 1]++;
                    }
                }
            }

            String rs = "";
            for (int i = 0; i < levels.length; i++) {
                var characters = availableVietnameseAccents.get(s.charAt(i));
                var level = levels[i];
                rs += characters.get(level);
            }
            sb.append(rs).append('*');

            loopCnt++;

        }
        return sb.toString();
    }


    public static String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }


}
