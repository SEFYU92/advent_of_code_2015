package org.example.day1;

public class ParenthesisElevator {
    public static Integer resolveFloor(String input) {
        var floor = 0;
        for (char symbol : input.toCharArray()) {
            switch (symbol) {
                case '(' -> floor++;
                case ')' -> floor--;
            }
        }
        return floor;
    }

    public static Integer resolveBasementPosition(String input) {
        var counter = 0;
        var floor = 0;
        for (char symbol : input.toCharArray()) {
            switch (symbol) {
                case '(' -> floor++;
                case ')' -> floor--;
            }
            counter++;
            if (floor < 0) {
                break;
            }
        }
        return counter;
    }
}
