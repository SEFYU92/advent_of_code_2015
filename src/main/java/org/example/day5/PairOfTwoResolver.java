package org.example.day5;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;

record Pair(String characterPair, int position) {
}

public class PairOfTwoResolver {
    public static boolean containsPairOfTwo(final String input) {
        var pairPositionMap = mapPositionsToPair(input);
        return pairPositionMap.entrySet().stream().filter(PairOfTwoResolver::isMultiple)
                .anyMatch(PairOfTwoResolver::isNotOverlaping);
    }

    private static boolean isNotOverlaping(Map.Entry<String, Set<Integer>> pairPositions) {
        Set<Integer> positions = pairPositions.getValue();
        if (positions.size() > 2) return true;
        return notSuccessivePositions(positions);
    }

    private static boolean notSuccessivePositions(Set<Integer> positions) {
        return positions.stream().reduce((x, y) -> Math.abs(x - y)).orElse(0) > 1;
    }

    private static boolean isMultiple(Map.Entry<String, Set<Integer>> x) {
        return x.getValue().size() > 1;
    }

    private static Map<String, Set<Integer>> mapPositionsToPair(String input) {
        return IntStream.range(0, input.length() - 1)
                .mapToObj(i -> new Pair(input.charAt(i) + String.valueOf(input.charAt(i + 1)), i))
                .collect(Collectors.groupingBy(Pair::characterPair, Collectors.mapping(Pair::position, toSet())));
    }
}
