package org.example.day3;

import java.util.*;

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

    public void visitAllHouses(String input) {
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
    }

    public List<String> splitInput(String input) {
        var input1 = new ArrayList<String>();
        var input2 = new ArrayList<String>();
        input.chars().forEach(x -> {
            if (input1.size() == input2.size()) {
                input1.add(String.valueOf((char) x));
            } else {
                input2.add(String.valueOf((char) x));
            }
        });
        return List.of(String.join("", input1), String.join("", input2));
    }

    private char castToChar(int x) {
        return (char) x;
    }

    public int countMultipleVisits() {
        return Set.copyOf(positionsHistory).size();
    }

    public Set<Position> getVisitedHouses() {
        return new HashSet<>(positionsHistory);
    }
}
