package org.example.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Position) obj;
        return this.x == that.x &&
                this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class SantaNavigationSystem {
    private Position currentPosition;

    private final List<Position> positionsHistory = new ArrayList<>();

    private char castToChar(int x) {
        return (char) x;
    }

    public void visitAllHouses(String input) {
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
    }

    public List<Position> getPositionsHistory() {
        return positionsHistory;
    }
}
