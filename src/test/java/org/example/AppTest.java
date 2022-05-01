package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {
    private String dayOneInput;
    private List<String> dayTwoInput;

    @BeforeAll
    void init() throws IOException {
        dayOneInput = Files.readString(Paths.get("./src/test/resources/day1_input"));
        dayTwoInput = Files.readAllLines(Paths.get("./src/test/resources/day2_input"));
    }

    @Test
    void day_1() {
        assertEquals(74, ParenthesisElevator.resolveFloor(dayOneInput));
    }

    @Test
    void day_1_part_2() {
        assertEquals(1795, ParenthesisElevator.resolveBasementPosition(dayOneInput));
    }

    @Test
    void day_2() {
        assertEquals(1606483, AreaWrappingCalculator.computeTotalArea(dayTwoInput));
    }

    @Test
    void day_2_part_2() {
        assertEquals(3842356, AreaWrappingCalculator.computeTotalRibbon(dayTwoInput));
    }

}
