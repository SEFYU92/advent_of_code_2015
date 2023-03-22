package org.example.day5;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class StringVerifier {
    private static final List<String> forbidden = List.of("ab", "cd", "pq", "xy");
    static Pattern threeVowels = Pattern.compile("\\w*[aeiou]\\w*[aeiou]\\w*[aeiou]\\w*");
    public static final Predicate<String> CONTAINS3VOWELS = x -> threeVowels.matcher(x).matches();
    public static final Predicate<String> CONTAINS_DOUBLE = x -> {
        for (int i = 1; i < x.length(); i++) {
            if (x.charAt(i) == x.charAt(i - 1)) return true;
        }
        return false;
    };
    public static final Predicate<String> DOES_NOT_CONTAIN_FORBIDDEN = x -> forbidden.stream().noneMatch(x::contains);
    public static final Predicate<String> CONTAINS_PAIR_OF_2 = StringVerifier::containsPairOfTwo;
    public static final Predicate<String> CONTAINS_REPEATED = StringVerifier::containsRepeated;

    @SafeVarargs
    public static boolean satisfiesAllPredicates(String input, Predicate<String>... conditions) {
        return Arrays.stream(conditions).filter (x -> x.test(input)).count() == conditions.length;
    }

    @SafeVarargs
    public static long numberOfNiceStrings(List<String> inputs, Predicate<String>... conditions) {
        return inputs.stream().filter(input -> satisfiesAllPredicates(input,conditions)).count();
    }

    public static boolean containsPairOfTwo(String input) {
        var pairList = new ArrayList<String>();
        for (int i = 0; i < input.length() - 1; i++) {
            pairList.add(input.charAt(i) + String.valueOf(input.charAt(i + 1)));
            if (i >= 1 && pairList.get(i).equals(pairList.get(i - 1))) return false;
        }
        return pairList.size() - Set.copyOf(pairList).size() > 0;
    }

    public static boolean containsRepeated(String input) {
        for (int i = 2; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 2)) {
                return true;
            }
        }
        return false;
    }
}
