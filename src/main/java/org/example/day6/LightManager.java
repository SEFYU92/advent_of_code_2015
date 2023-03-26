package org.example.day6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.day6.InstructionType.*;

public class LightManager {
    public static final Map<List<Integer>, Boolean> lightGrid = new HashMap<>();

    public record Instruction(Integer x1, Integer x2, Integer y1, Integer y2, InstructionType instructionType) {
    }

    public static Instruction parseLine(String line) {
        var first7chars = line.substring(0, 7);
        InstructionType instructionType = null;
        switch (first7chars) {
            case "turn on" -> instructionType = ON;
            case "turn of" -> instructionType = OFF;
            case "toggle " -> instructionType = TOGGLE;
        }
        var subStrings = line.split(" ");
        var x1 = Integer.valueOf(subStrings[subStrings.length - 3].split(",")[0]);
        var x2 = Integer.valueOf(subStrings[subStrings.length - 3].split(",")[1]);
        var y1 = Integer.valueOf(subStrings[subStrings.length - 1].split(",")[0]);
        var y2 = Integer.valueOf(subStrings[subStrings.length - 1].split(",")[1]);
        return new Instruction(x1, x2, y1, y2, instructionType);
    }
}
