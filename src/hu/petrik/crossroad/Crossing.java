package hu.petrik.crossroad;

import java.util.Random;

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
            }
        } catch (InterruptedException e) {
            System.out.println("Lamp got interrupted!");
        }
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
