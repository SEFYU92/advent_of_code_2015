package org.example;

import org.example.day3.SantaNavigationSystem;
import org.example.day4.HashProducer;
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
    private String dayThreeInput;

    @BeforeAll
    void init() throws IOException {
        dayOneInput = Files.readString(Paths.get("./src/test/resources/day1_input"));
        dayTwoInput = Files.readAllLines(Paths.get("./src/test/resources/day2_input"));
        dayThreeInput = Files.readString(Paths.get("./src/test/resources/day3_input"));
    }

    @Test
    void day_1() {
        assertEquals(ParenthesisElevator.resolveFloor(dayOneInput), 74);
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

    @Test
    void day_3() {
        var santaNavigationSystem = new SantaNavigationSystem();
        santaNavigationSystem.visitAllHouses(dayThreeInput);
        assertEquals(2565, santaNavigationSystem.countMultipleVisits());
    }

    @Test
    void test_split_input_even_size() {
        var santaNavigationSystem = new SantaNavigationSystem();
        var result = santaNavigationSystem.splitInput("azerty");
        assertEquals("aet", result.get(0));
        assertEquals("zry", result.get(1));
    }

    @Test
    void test_split_input_odd_size() {
        var santaNavigationSystem = new SantaNavigationSystem();
        var result = santaNavigationSystem.splitInput("12345");
        assertEquals("135", result.get(0));
        assertEquals("24", result.get(1));
    }

    @Test
    void day_3_part_2() {
        var santaNavigationSystem = new SantaNavigationSystem();
        var robotSantaNavigationSystem = new SantaNavigationSystem();
        var santaAndRobotSantaRoadMap = santaNavigationSystem.splitInput(dayThreeInput);
        santaNavigationSystem.visitAllHouses(santaAndRobotSantaRoadMap.get(0));
        robotSantaNavigationSystem.visitAllHouses(santaAndRobotSantaRoadMap.get(1));
        var allVisitedHouses = santaNavigationSystem.getVisitedHouses();
        allVisitedHouses.addAll(robotSantaNavigationSystem.getVisitedHouses());
        assertEquals(2639, allVisitedHouses.size());
    }

    @Test
    void day4_part_1() {
        var hashProducer = new HashProducer("00000");
        assertEquals("609043", hashProducer.produceHash("abcdef"));
        assertEquals("1048970", hashProducer.produceHash("pqrstuv"));
        assertEquals("254575", hashProducer.produceHash("bgvyzdsv"));
    }

    @Test
    void day4_part_2() {
        var hashProducer = new HashProducer("000000");
        assertEquals("1038736", hashProducer.produceHash("bgvyzdsv"));
    }
}
