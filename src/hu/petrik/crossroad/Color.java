package hu.petrik.crossroad;

public enum Color {
    BLACK_FORE("\u001B[30m"),
    BLACK_BACK("\u001B[40m"),
    RED_FORE("\u001B[31m"),
    RED_BACK("\u001B[41m"),
    GREEN_FORE("\u001B[32m"),
    GREEN_BACK("\u001B[42m"),
    YELLOW_FORE("\u001B[33m"),
    YELLOW_BACK("\u001B[43m"),
    BLUE_FORE("\u001B[34m"),
    BLUE_BACK("\u001B[44m"),
    PURPLE_FORE("\u001B[35m"),
    PURPLE_BACK("\u001B[45m"),
    CYAN_FORE("\u001B[36m"),
    CYAN_BACK("\u001B[46m"),
    WHITE_FORE("\u001B[37m"),
    WHITE_BACK("\u001B[47m");

    String color;
    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.color;
    }
}
