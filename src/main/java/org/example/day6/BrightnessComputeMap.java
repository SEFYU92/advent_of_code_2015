package org.example.day6;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiFunction;

public class BrightnessComputeMap extends EnumMap<InstructionType, BiFunction<List<Integer>, Integer, Integer>> {
    public BrightnessComputeMap() {
        super(InstructionType.class);
        this.put(InstructionType.ON, (k, v) -> v == null ? 1 : ++v);
        this.put(InstructionType.OFF, (k, v) -> v != null && v > 0 ? --v : v);
        this.put(InstructionType.TOGGLE, (k, v) -> v == null ? 2 : v + 2);
    }
}
