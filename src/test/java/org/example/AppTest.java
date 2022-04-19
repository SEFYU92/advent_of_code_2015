package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @ParameterizedTest
    @CsvSource({"(()),0", "()(),0", "(((,3", "(()(()(,3", "))(((((,3", "()),-1", "))(,-1", "))),-3", ")())()),-3"})
    void day_1(String input, Integer floorLevel) {
        assertEquals(floorLevel, ParenthesisElevator.resolveFloor(input));
    }
}
