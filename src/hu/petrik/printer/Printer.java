package hu.petrik.printer;


import java.util.LinkedList;
import java.util.Queue;
import java.util.SplittableRandom;

public class Printer {

    private int size;
    private Queue<String> printingQueue;

    public Printer(int size) {
        this.size = size;
        this.printingQueue = new LinkedList<>();
    }

    public synchronized void assignTask(String toPrint) {
        if (printingQueue.size() == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Added to queue: " + toPrint);
        printingQueue.add(toPrint);
    }

    public synchronized String print(long time) {
        if (printingQueue.isEmpty()) {
            try {
                wait(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String out = printingQueue.remove();
        System.out.print("Printed " + out);
        return out;
    }
}
