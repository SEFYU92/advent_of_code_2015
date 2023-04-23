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
            return resolveValue(operation[0], input, signals);
        }

        if (operation.length == 2) {
            return  ~resolveValue(operation[1],input, signals) & 0xffff;
        }

        if (operation.length == 3) {
            if (operation[1].equals("AND")) {
                var value1 = resolveValue(operation[0], input, signals);
                var value2 = resolveValue(operation[2], input, signals);
                return value1 & value2;
            }
            if (operation[1].equals("OR")) {
                var value1 = resolveValue(operation[0], input, signals);
                var value2 = resolveValue(operation[2], input, signals);
                return value1 | value2;
            }
            if (operation[1].equals("RSHIFT")) {
                var value1 = resolveValue(operation[0], input, signals);
                return value1 >> Integer.parseInt(operation[2]);
            }
            if (operation[1].equals("LSHIFT")) {
                var value1 = resolveValue(operation[0], input, signals);
                return value1 << Integer.parseInt(operation[2]);
            }
        }
        return 0;
    }

    private static int resolveValue(String value, List<String> input, Map<String, Integer> signals) {
        Integer intValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            intValue = signals.get(value);
            if (intValue != null) {
                return intValue;
            }
            intValue = resolve(input, value, signals);
            signals.put(value, intValue);
            return intValue;
        }
    }
}
