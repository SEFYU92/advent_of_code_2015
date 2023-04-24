package org.example.day7;

import java.util.List;
import java.util.Map;

public class AlterSignalResolving extends SignalResolving{
    private final String alteredSignal;
    private final int value;

    public AlterSignalResolving(String alteredSignal, int value) {
        this.alteredSignal = alteredSignal;
        this.value = value;
    }

    @Override
    protected int resolveValue(String value, List<String> input, Map<String, Integer> signals) {
        if(value.equals(alteredSignal)) return this.value;
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
