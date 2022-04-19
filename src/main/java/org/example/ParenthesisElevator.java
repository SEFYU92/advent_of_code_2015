package org.example;

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
}
