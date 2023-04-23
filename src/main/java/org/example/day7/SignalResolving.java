package org.example.day7;

import java.util.List;
import java.util.Map;

public class SignalResolving {
    private SignalResolving() {
        //private constructor to hide default
    }

    public static Integer resolve(List<String> input, String signal, Map<String, Integer> signals) {
        var line = input.stream().map(x -> x.split(" -> ")).filter(x -> x[1].equals(signal)).findFirst().orElseThrow();
        var operation = line[0].split(" ");

        if (operation.length == 1) {
            var value = operation[0];
            try {
                return Integer.valueOf(value);
            } catch (NumberFormatException e) {
                var intValue = signals.get(value);
                if (intValue != null) {
                    return intValue;
                }
                intValue = resolve(input, value, signals);
                signals.put(value, intValue);
                return intValue;
            }
        }

        if (operation.length == 2) {
            var value = operation[1];
            try {
                return ~Integer.parseInt(value) & 0xffff;
            } catch (NumberFormatException e) {
                var intValue = signals.get(value);
                if (intValue != null) {
                    return ~intValue & 0xffff;
                }
                intValue = resolve(input, value, signals);
                signals.put(value, intValue);
                return ~intValue & 0xffff;
            }
        }
        return 0;
    }
}
