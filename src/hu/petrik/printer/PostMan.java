package hu.petrik.printer;

public class PostMan extends Thread {
    private Printer printer;

    public PostMan(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        System.out.println("Postman starts working");
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = printer.print(1500);
            Office.mails.add(s);
            System.out.println("Printed, mailed: " + s);
        }
    }
}
