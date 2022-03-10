package hu.petrik.crossroad;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Map map = new Map("map.csv");
            City city = new City(map);
            city.addCar(new Car(map, 5, 8));
            city.addCar(new Car(map, 5, 8));
            city.addCar(new Car(map, 2, 1));
            city.addCar(new Car(map, 6, 8));
            city.printState();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
