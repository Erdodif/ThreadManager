package hu.petrik.crossroad;

import java.util.Random;

public class Car {
    private Position position;
    private Direction direction;
    private Direction from;
    private Random random;
    private int speed;
    private Map map;

    public Car(Map map, int x, int y) {
        this.position = new Position(x, y);
        random = new Random();
        this.direction = Direction.fromWay(random.nextInt(4));
        this.from = null;
        this.map = map;
        this.speed = (random.nextInt(40)+20)*7;
    }

    public Car(Map map, Position position) {
        this.position = position;
        random = new Random();
        this.direction = Direction.fromWay(random.nextInt(4));
        this.from = null;
        this.map = map;
        this.speed = (random.nextInt(40)+20)*7;
    }

    public synchronized void start(){
        System.out.println("Car started!");
        try {
            while (true){
                wait(speed);
                moveOrTurn();
                map.notifyStateChanged();
            }
        }
        catch (InterruptedException e){
            System.out.println("Car got interrupted");
        }
    }

    public void moveOrTurn() {
        if (canMoveForward()) {
            this.from = Direction.turnAround(this.direction);
            this.position = getPositionAhead();
        } else {
            this.direction = random.nextBoolean() ? Direction.rotateLeft(this.direction) : Direction.rotateRight(this.direction);
            if (!canMoveForward()) {
                this.direction = Direction.turnAround(this.direction);
            }
            if (!canMoveForward()) {
                this.direction = this.from;
            }
        }
    }

    public Position getPositionAhead() {
        switch (getDirection()) {
            case UP:
                return this.position.getAbove(map.getHeight());
            case DOWN:
                return this.position.getBelow(map.getHeight());
            case LEFT:
                return this.position.getToLeft(map.getWidth());
            default:
                return this.position.getToRight(map.getWidth());
        }
    }

    public Road getCurrentRoad() {
        return map.getRoad(this.position.getX(), this.position.getY());
    }

    private boolean canMoveForward() {
        Road position = getCurrentRoad();
        switch (direction) {
            case UP:
                return position.isUp();
            case DOWN:
                return position.isDown();
            case LEFT:
                return position.isLeft();
            default:
                return position.isRight();
        }
    }

    public int getCoordinateX() {
        return position.getX();
    }

    public int getCoordinateY() {
        return position.getY();
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
