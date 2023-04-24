package org.example.day7;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignalResolvingTest {
    @Test
    void should_recursively_resolve_simple_assignations() {
        var input = List.of(
                "123 -> x",
                "x -> y",
                "y -> z"
        );
        var result = new SignalResolving().resolve(input, "z", new HashMap<>());

        assertEquals(123, result);
    }

    @Test
    void should_recursively_resolve_NOT_operator() {
        var input = List.of(
                "123 -> x",
                "x -> y",
                "NOT y -> z"
        );
        var result = new SignalResolving().resolve(input, "z", new HashMap<>());

        assertEquals(65412, result);
    }

    @Test
    void should_recursively_resolve_AND_operator() {
        var input = List.of(
                "123 -> x",
                "456 -> y",
                "x AND y -> d"
        );
        var result = new SignalResolving().resolve(input, "d", new HashMap<>());

        assertEquals(72, result);
    }

    @Test
    void should_recursively_resolve_OR_operator() {
        var input = List.of(
                "123 -> x",
                "456 -> y",
                "x OR y -> d"
        );
        var result = new SignalResolving().resolve(input, "d", new HashMap<>());

        assertEquals(507, result);
    }

    @Test
    void should_recursively_resolve_RSHIFT_operator() {
        var input = List.of(
                "123 -> x",
                "456 -> y",
                "x AND y -> d",
                "x OR y -> e",
                "x LSHIFT 2 -> f",
                "y RSHIFT 2 -> g",
                "NOT x -> h",
                "NOT y -> i"
        );

        var result = new SignalResolving().resolve(input, "g", new HashMap<>());

        assertEquals(114, result);
    }

    @Test
    void should_recursively_resolve_LSHIFT_operator() {
        var input = List.of(
                "123 -> x",
                "456 -> y",
                "x AND y -> d",
                "x OR y -> e",
                "x LSHIFT 2 -> f",
                "y RSHIFT 2 -> g",
                "NOT x -> h",
                "NOT y -> i"
        );

        var result = new SignalResolving().resolve(input, "f", new HashMap<>());

        assertEquals(492, result);
    }
}