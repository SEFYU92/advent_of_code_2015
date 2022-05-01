package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class AreaWrappingCalculator {
    public static final Function<String, String[]> LINE_TO_DIMENSIONS = x -> x.split("x");
    public static final ToIntFunction<String[]> CALCULATE_TOTAL_AREA = x -> {
        var l = Integer.valueOf(x[0]);
        var w = Integer.valueOf(x[1]);
        var h = Integer.valueOf(x[2]);
        var area_1 = l * w;
        var area_2 = h * w;
        var area_3 = h * l;
        var min_area = List.of(area_1, area_2, area_3).stream().min(Comparator.naturalOrder()).get();
        return 2 * area_1 + 2 * area_2 + 2 * area_3 + min_area;
    };

    public static final ToIntFunction<String[]> CALCULATE_TOTAL_RIBBON = x -> {
        var l = Integer.valueOf(x[0]);
        var w = Integer.valueOf(x[1]);
        var h = Integer.valueOf(x[2]);
        var perimeter_1 = 2 * l + 2 * w;
        var perimeter_2 = 2 * h + 2 * w;
        var perimeter_3 = 2 * h + 2 * l;
        var min_perimeter = List.of(perimeter_1, perimeter_2, perimeter_3)
                .stream()
                .min(Comparator.naturalOrder())
                .get();
        return min_perimeter + l * w * h;
    };

    public static int computeTotalArea(List<String> input) {
        return applyProcessingToInputLines(input, CALCULATE_TOTAL_AREA);
    }

    public static int computeTotalRibbon(List<String> input) {
        return applyProcessingToInputLines(input, CALCULATE_TOTAL_RIBBON);
    }

    private static int applyProcessingToInputLines(List<String> input, ToIntFunction<String[]> processing) {
        return input.stream().map(LINE_TO_DIMENSIONS).mapToInt(processing).sum();
    }
}
