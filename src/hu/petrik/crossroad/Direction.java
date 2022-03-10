package hu.petrik.crossroad;

public enum Direction {
    UP(0, "up", "↑"),
    RIGHT(1, "right", "→"),
    DOWN(2, "down", "↓"),
    LEFT(3, "left", "←");

    private final String name;
    private final String arrow;
    private final int way;

    Direction(int way, String name, String arrow) {
        this.name = name;
        this.arrow = arrow;
        this.way = way;
    }

    public String getName() {
        return name;
    }

    public String getArrow() {
        return arrow;
    }

    public int getWay() {
        return way;
    }

    public static Direction rotateLeft(Direction direction) {
        return Direction.fromWay(direction.way - 1);
    }

    public static Direction rotateRight(Direction direction) {
        return Direction.fromWay(direction.way + 1);
    }

    public static Direction turnAround(Direction direction) {
        return Direction.fromWay(direction.way + 2);
    }

    public static Direction fromWay(int way) {
        switch (way % 4) {
            case 0:
                return UP;
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
            default:
                return LEFT;
        }
    }
}
