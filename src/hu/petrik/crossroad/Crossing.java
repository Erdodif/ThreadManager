package hu.petrik.crossroad;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Crossing extends Road {

    private boolean updown = false;
    private final int speed;
    private boolean yellow = false;
    private final Random random = new Random();

    public Crossing() {
        this.speed = (random.nextInt(10) + 10) * 400;
    }

    public synchronized void operate() {
        try {
            wait((random.nextInt(10) + 10) * 40);
            while (true){
                wait(speed / 20 * 12);
                yellow = true;
                wait(speed / 4);
                yellow = false;
                updown = !updown;
                notifyAll();
            }
        } catch (InterruptedException e) {
            System.out.println("Lamp got interrupted!");
        }
    }

    public boolean canPass(Direction direction){
        if(isYellow()){
            return false;
        }
        if(updown){
            return direction == Direction.UP || direction == Direction.DOWN;
        }
        return direction == Direction.LEFT || direction == Direction.RIGHT;
    }

    public boolean isUpdown() {
        return updown;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isYellow() {
        return yellow;
    }

    @Override
    public String toString() {
        return updown ? "↕" : "↔";
    }
}
