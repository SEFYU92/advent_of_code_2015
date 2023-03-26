package org.example.day6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightManager {
    public long resolveLightProgram(List<String> input) {
        Map<List<Integer>, Boolean> lightGrid = new HashMap<>();
        input.stream().map(LightInstructionParser::parseLine)
                .forEach(instruction -> InstructionExecuter.executeInstruction(instruction,lightGrid));
        return lightGrid.values().stream().filter(Boolean::booleanValue).count();
    }

    @SuppressWarnings(value = "unused")
    public long resolveBrightnessProgram(List<String> input) {
        Map<List<Integer>, Integer> lightGrid = new HashMap<>();
        input.stream().map(LightInstructionParser::parseLine)
                .forEach(instruction -> InstructionExecuter.executeBrightnessInstruction(instruction,lightGrid));
        return lightGrid.values().stream().mapToInt(Integer::intValue).sum();
    }
}
