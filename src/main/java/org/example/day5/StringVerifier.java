package org.example.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class StringVerifier {
    private static final List<String> forbidden = List.of("ab", "cd", "pq", "xy");
    private static final Pattern threeVowels = Pattern.compile("\\w*[aeiou]\\w*[aeiou]\\w*[aeiou]\\w*");

    public static final Predicate<String> CONTAINS_3_VOWELS = x -> threeVowels.matcher(x).matches();
    public static final Predicate<String> CONTAINS_SUCCESSIVE_DOUBLE = StringVerifier::containsSuccessiveDouble;
    public static final Predicate<String> DOES_NOT_CONTAIN_FORBIDDEN = x -> forbidden.stream().noneMatch(x::contains);
    public static final Predicate<String> CONTAINS_PAIR_OF_2 = StringVerifier::containsPairOfTwo;
    public static final Predicate<String> CONTAINS_SPACED_DOUBLE = StringVerifier::containsSpacedDouble;

    private final List<Predicate<String>> conditions;

    public StringVerifier(List<Predicate<String>> conditions) {
        this.conditions = conditions;
    }

    private boolean satisfiesAllConditions(String input) {
        return conditions.stream().filter (x -> x.test(input)).count() == conditions.size();
    }

    public long numberOfNiceStrings(List<String> inputs) {
        return inputs.stream().filter(this::satisfiesAllConditions).count();
    }

    private static boolean containsPairOfTwo(String input) {
        var pairList = new ArrayList<String>();
        for (int i = 0; i < input.length() - 1; i++) {
            pairList.add(input.charAt(i) + String.valueOf(input.charAt(i + 1)));
            if (i >= 1 && pairList.get(i).equals(pairList.get(i - 1))) return false;
        }
        return pairList.size() - Set.copyOf(pairList).size() > 0;
    }

    private static boolean containsSpacedDouble(String input) {
        for (int i = 2; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsSuccessiveDouble(String x) {
        for (int i = 1; i < x.length(); i++) {
            if (x.charAt(i) == x.charAt(i - 1)) return true;
        }
        return false;
    }
}
