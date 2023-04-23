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
        var result = SignalResolving.resolve(input, "z", new HashMap<>());

        assertEquals(123,result);
    }

    @Test
    void should_recursively_resolve_NOT_operator() {
        var input = List.of(
                "123 -> x",
                "x -> y",
                "NOT y -> z"
        );
        var result = SignalResolving.resolve(input, "z", new HashMap<>());

        assertEquals(65412,result);
    }
}