package hu.petrik.crossroad;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Map map = new Map("map.csv");
            City city = new City(map);
            map.addDrawer(city.getDrawer());
            city.placeRandomCars(15);
            Thread changes = new Thread(()->{
                city.getDrawer().monitorChanges();
            });
            changes.start();
            new Thread(map::notifyStateChanged).start();
            new Thread(map::startLamps).start();
            new Thread(city::runCars).start();
            new Thread(()->{
                Scanner sc = new Scanner(System.in);
                sc.next("c");
                changes.interrupt();
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
