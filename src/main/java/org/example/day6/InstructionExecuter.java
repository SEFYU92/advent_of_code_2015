package org.example.day6;

import java.util.List;
import java.util.Map;

public class InstructionExecuter {
    public static final LightComputeMap lightComputeMap = new LightComputeMap();

    public static void executeInstruction(Instruction instruction, Map<List<Integer>, Boolean> lightGrid) {
        for (int i = instruction.x1(); i <= instruction.y1(); i++) {
            for (int j = instruction.x2(); j <= instruction.y2(); j++) {
                lightGrid.compute(List.of(i, j), lightComputeMap.get(instruction.instructionType()));
            }
        }
    }
}
