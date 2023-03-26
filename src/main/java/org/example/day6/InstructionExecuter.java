package org.example.day6;

import java.util.List;
import java.util.Map;

public class InstructionExecuter {
    public static final LightComputeMap LIGHT_COMPUTE_MAP = new LightComputeMap();
    public static final BrightnessComputeMap BRIGHTNESS_COMPUTE_MAP = new BrightnessComputeMap();

    public static void executeInstruction(Instruction instruction, Map<List<Integer>, Boolean> lightGrid) {
        for (int i = instruction.x1(); i <= instruction.y1(); i++) {
            for (int j = instruction.x2(); j <= instruction.y2(); j++) {
                lightGrid.compute(List.of(i, j), LIGHT_COMPUTE_MAP.get(instruction.instructionType()));
            }
        }
    }

    public static void executeBrightnessInstruction(Instruction instruction, Map<List<Integer>, Integer> lightGrid) {
        for (int i = instruction.x1(); i <= instruction.y1(); i++) {
            for (int j = instruction.x2(); j <= instruction.y2(); j++) {
                lightGrid.compute(List.of(i, j), BRIGHTNESS_COMPUTE_MAP.get(instruction.instructionType()));
            }
        }
    }

}
