package hu.petrik.crossroad;

public enum Direction {
    UP(0, "up", "↑"),
    DOWN(1, "down", "↓"),
    LEFT(2, "left", "←"),
    RIGHT(3, "right", "→");

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

    public static Direction fromWay(int way) {
        switch (way) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return LEFT;
            case 3:
                return RIGHT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
