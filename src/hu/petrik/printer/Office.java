package hu.petrik.printer;

import java.util.ArrayList;

public class Office {
    private Printer printer;
    private Worker worker;
    static public ArrayList<String> mails;
    private PostMan postMan;

    public Office(){
        mails = new ArrayList<>();
        this.printer = new Printer(10);
        this.worker = new Worker(printer);
        this.worker.start();
        this.postMan = new PostMan(printer);
        this.postMan.start();
        while (true){
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Printed mails("+mails.size()+"): ");
            for (String mail:mails) {
                System.out.println(mail);
            }
            System.out.println();
        }
    }
}
