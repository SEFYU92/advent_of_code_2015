package org.example.day6;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiFunction;

public class LightComputeMap extends EnumMap<InstructionType, BiFunction<List<Integer>, Boolean, Boolean>> {
    public LightComputeMap() {
        super(InstructionType.class);
        this.put(InstructionType.ON, (k, v) -> true);
        this.put(InstructionType.OFF, (k, v) -> false);
        this.put(InstructionType.TOGGLE, (k, v) -> v == null || !v);
    }
}
