package hu.petrik.crossroad;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Map map = new Map("map.csv");
            map.addCar(new Car(5,8));
            map.addCar(new Car(5,8));
            map.addCar(new Car(2,1));
            map.addCar(new Car(6,8));
            map.printState();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
