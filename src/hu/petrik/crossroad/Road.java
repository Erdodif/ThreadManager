package hu.petrik.crossroad;

public class Road {
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }


    @Override
    public String toString() {
        if (up) {
            if (down) {
                if (left) {
                    if (right) {
                        return "╬";
                    }
                    return "╣";
                }
                if (right) {
                    return "╠";
                }
                return "║";
            }
            if (left) {
                if (right) {
                    return "╩";
                }
                return "╝";
            }
            if (right) {
                return "╚";
            }
            return "↑";
        }
        if (down) {
            if (left) {
                if (right) {
                    return "╦";
                }
                return "╗";
            }
            if (right) {
                return "╔";
            }
            return "↓";
        }
        if (left) {
            if (right) {
                return "═";
            }
            return "←";
        }
        if (right) {
            return "→";//no need
        }
        return "X";
    }
}
