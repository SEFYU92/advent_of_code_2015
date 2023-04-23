package org.example;

import org.example.day1.ParenthesisElevator;
import org.example.day2.AreaWrappingCalculator;
import org.example.day3.SantaNavigationSystem;
import org.example.day4.HashProducer;
import org.example.day5.StringVerifier;
import org.example.day6.LightManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.example.day5.StringVerifier.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppTest {
    private String dayOneInput;
    private List<String> dayTwoInput;
    private String dayThreeInput;
    private List<String> dayFiveInput;
    private List<String> daySixInput;
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private List<String> daySevenInput;

    @BeforeAll
    void init() throws IOException {
        dayOneInput = Files.readString(Paths.get("./src/test/resources/day1_input"));
        dayTwoInput = Files.readAllLines(Paths.get("./src/test/resources/day2_input"));
        dayThreeInput = Files.readString(Paths.get("./src/test/resources/day3_input"));
        dayFiveInput = Files.readAllLines(Paths.get("./src/test/resources/day5_input"));
        daySixInput = Files.readAllLines(Paths.get("./src/test/resources/day6_input"));
        daySevenInput = Files.readAllLines(Paths.get("./src/test/resources/day7_input"));
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

    @Test
    void day4_try_with_concurrency() {
        var hashProducer = new HashProducer("000000");
        assertEquals("1038736", hashProducer.produceConcurrentHash("bgvyzdsv"));
    }

    @Test
    void day5() {
        var stringVerifier = new StringVerifier(List.of(CONTAINS_3_VOWELS, CONTAINS_SUCCESSIVE_DOUBLE, DOES_NOT_CONTAIN_FORBIDDEN));
        assertEquals(238, stringVerifier.numberOfNiceStrings(dayFiveInput));
    }

    @Test
    void day5part2() {
        var stringVerifier = new StringVerifier(List.of(CONTAINS_PAIR_OF_2, CONTAINS_SPACED_DOUBLE));
        assertAll(
                () -> assertEquals(1, stringVerifier.numberOfNiceStrings(List.of("qjhvhtzxzqqjkmpb"))),
                () -> assertEquals(1, stringVerifier.numberOfNiceStrings(List.of("xxyxx"))),
                () -> assertEquals(1, stringVerifier.numberOfNiceStrings(List.of("aaaaxyx"))),
                () -> assertEquals(69, stringVerifier.numberOfNiceStrings(dayFiveInput)),
                () -> assertEquals(0, stringVerifier.numberOfNiceStrings(List.of("uurcxstgmygtbstg"))),
                () -> assertEquals(0, stringVerifier.numberOfNiceStrings(List.of("uurcxstgmygtbstg"))),
                () -> assertEquals(0, stringVerifier.numberOfNiceStrings(List.of("ieodomkazucvgmuy")))
        );
    }

    @Test
    void day6() {
        var lightManager = new LightManager();
        assertAll(
                () -> assertEquals(9, lightManager.resolveLightProgram(List.of("turn on 0,0 through 2,2"))),
                () -> assertEquals(1000, lightManager.resolveLightProgram(List.of("toggle 0,0 through 999,0"))),
                () -> assertEquals(4, lightManager.resolveLightProgram(List.of("toggle 499,499 through 500,500"))),
                () -> assertEquals(569999, lightManager.resolveLightProgram(daySixInput)),
                () -> assertEquals(9, lightManager.resolveBrightnessProgram(List.of("turn on 0,0 through 2,2"))),
                () -> assertEquals(18, lightManager.resolveBrightnessProgram(List.of("toggle 0,0 through 2,2")))
        );
    }

    @Test
    void day6_part2() {
        var lightManager = new LightManager();
        assertEquals(17836115, lightManager.resolveBrightnessProgram(daySixInput));
    }
}
