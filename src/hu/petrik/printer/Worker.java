package hu.petrik.printer;

public class Worker extends Thread {
    private String[] work = {"first", "second", "third", "fourth", "fifth"};
    private int counter = 0;
    private Printer printer;

    public Worker(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        System.out.println("Worker starts working");
        while (true) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printer.assignTask(work[counter]);
            System.out.println("Sent to printer: " + work[counter]);
            counter++;
            if (counter == work.length) {
                counter = 0;
            }
        }
    }
}
