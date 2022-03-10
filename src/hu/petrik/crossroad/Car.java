package hu.petrik.crossroad;

import java.util.Random;

public class Car {
    private int coordinateX;
    private int coordinateY;
    private Direction direction;
    private Direction from;
    private Random random;

    public Car(int x, int y){
        this.coordinateX = x;
        this.coordinateY = y;
        random = new Random();
        this.direction = Direction.fromWay(random.nextInt(4));
        this.from = null;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return direction.getArrow();
    }
}
