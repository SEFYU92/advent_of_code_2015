package org.example.day6;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class LightManager {
    public static final Map<List<Integer>, Boolean> lightGrid = new HashMap<>();
    public static final EnumMap<InstructionType, BiFunction<List<Integer>, Boolean, Boolean>> lightComputeMap;

    static {
        lightComputeMap = new EnumMap<>(InstructionType.class);
        lightComputeMap.put(InstructionType.ON, (k, v) -> true);
        lightComputeMap.put(InstructionType.OFF, (k, v) -> false);
        lightComputeMap.put(InstructionType.TOGGLE, (k, v) -> v == null || !v);
    }

    public static void executeInstruction(Instruction instruction) {
        for (int i = instruction.x1(); i <= instruction.y1(); i++) {
            for (int j = instruction.x2(); j <= instruction.y2(); j++) {
                lightGrid.compute(List.of(i, j), lightComputeMap.get(instruction.instructionType()));
            }
        }
    }
}
