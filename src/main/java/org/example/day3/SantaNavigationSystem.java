package org.example.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

final class Position {
    private final int x;
    private final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Position up() {
        return new Position(x + 1, y);
    }

    Position down() {
        return new Position(x - 1, y);
    }

    Position right() {
        return new Position(x, y + 1);
    }

    Position left() {
        return new Position(x, y - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class SantaNavigationSystem {
    private Position currentPosition;

    private List<Position> positionsHistory;

    public int visitAllHouses(String input) {
        positionsHistory = new ArrayList<>();
        currentPosition = new Position(0, 0);
        positionsHistory.add(currentPosition);
        input.chars().map(this::castToChar).forEach(x -> {
                    currentPosition = positionsHistory.get(positionsHistory.size() - 1);
                    switch (x) {
                        case '^' -> positionsHistory.add(currentPosition.up());
                        case 'v' -> positionsHistory.add(currentPosition.down());
                        case '>' -> positionsHistory.add(currentPosition.right());
                        case '<' -> positionsHistory.add(currentPosition.left());
                    }
                }
        );
        return countMultipleVisits();
    }

    private char castToChar(int x) {
        return (char) x;
    }

    private int countMultipleVisits() {
        return Set.copyOf(positionsHistory).size();
    }
}
