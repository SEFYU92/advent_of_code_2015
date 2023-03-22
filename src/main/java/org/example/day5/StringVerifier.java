package org.example.day5;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class StringVerifier {
    private static final List<String> forbidden = List.of("ab", "cd", "pq", "xy");
    static Pattern threeVoels = Pattern.compile("\\w*[aeiou]\\w*[aeiou]\\w*[aeiou]\\w*");
    private static final Predicate<String> CONTAINS3VOELS = x -> threeVoels.matcher(x).matches();
    private static final Predicate<String> CONTAINSDOUBLE = x -> {
        for (int i = 1; i < x.length(); i++) {
            if(x.charAt(i) == x.charAt(i-1)) return true;
        }
        return false;
    };

    private static final Predicate<String> DOESNT_CONTAIN_FORBIDDEN = x -> forbidden.stream().noneMatch(x::contains);

    public static boolean isNice(String input) {
        return Optional.of(input)
                .filter(CONTAINS3VOELS)
                .filter(CONTAINSDOUBLE)
                .filter(DOESNT_CONTAIN_FORBIDDEN)
                .isPresent();
    }

    public static int countNiceStrings(List<String> strings) {
        return (int) strings.stream().filter(StringVerifier::isNice).count();
    }
}
