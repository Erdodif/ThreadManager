package hu.petrik.crossroad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class City {
    public static String defaultStyle = Color.BLACK_BACK.toString() + Color.WHITE_FORE.toString();
    private Map map;
    private List<Car> cars;
    private Drawer drawer = new Drawer();
    Random random;

    public City(Map map) {
        this.map = map;
        this.cars = new ArrayList<>();
        this.random = new Random();
    }

    public City(Map map, ArrayList<Car> cars) {
        this.map = map;
        this.cars = cars;
        this.random = new Random();
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void placeRandomCar() {
        int x = random.nextInt(map.getHeight());
        int y = random.nextInt(map.getWidth());
        while (this.map.getRoad(x, y) == null) {
            x = random.nextInt(map.getHeight());
            y = random.nextInt(map.getWidth());
        }
        this.cars.add(new Car(this.map, x, y));
    }

    public void placeRandomCars(int amount) {
        for (int i = 0; i < amount; i++) {
            this.placeRandomCar();
        }
    }

    public void runCars() {
        for (Car car : cars) {
            new Thread(car::start).start();
        }
    }

    public void printState() {
        System.out.println("Notifying drawer from city");
        synchronized (this.drawer) {
            drawer.notify();
        }
    }

    public Drawer getDrawer() {
        return drawer;
    }

    @Override
    public String toString() {
        StringBuilder boby = new StringBuilder();
        for (Car car : cars) {
            boby.append(car.toString());
        }
        return "Cars: " + boby + " \n" + map.toString();
    }

    public class Drawer {
        public synchronized void monitorChanges() {
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    wait();
                    printState();
                } catch (InterruptedException e) {
                    System.out.println("Got interrupted");
                    interrupted = true;
                }
            }
        }

        public void printState() {
            System.out.print("\033[H\033[2J");
            System.out.println("\n\n\n");
            System.out.flush();
            StringBuilder boby = new StringBuilder();
            for (Car car : cars) {
                boby.append(car.toString());
            }
            ArrayList<Car> carsTemp = new ArrayList<>(cars);
            for (int i = 0; i < map.getHeight(); i++) {
                for (int j = 0; j < map.getWidth(); j++) {
                    if (map.getRoad(i, j) == null) {
                        printColored(Color.GREEN_BACK, " ");
                    } else {
                        int finalJ = j;
                        int finalI = i;
                        Stream<Car> onRoad = carsTemp.stream()
                                .filter(car -> car.getCoordinateX() == finalI && car.getCoordinateY() == finalJ);
                        long count = onRoad.count();
                        switch ((int) count) {
                            case 0:
                                Road road = map.getRoad(i, j);
                                if (road instanceof Crossing && ((Crossing)road).isYellow()) {
                                    printColored(Color.YELLOW_BACK,Color.BLACK_FORE,road.toString());
                                    break;
                                }
                                printColored(Color.WHITE_BACK, Color.BLACK_FORE, map.getRoad(i, j).toString());
                                break;
                            case 1:
                                onRoad = carsTemp.stream()
                                        .filter(car -> car.getCoordinateX() == finalI && car.getCoordinateY() == finalJ);
                                Car toPrint = onRoad.collect(Collectors.toList()).get(0);
                                printColored(Color.CYAN_BACK, Color.BLACK_FORE, toPrint.toString());
                                break;
                            default:
                                if (count > 10) {
                                    printColored(Color.WHITE_BACK, Color.RED_FORE, "X");
                                    break;
                                }
                                printColored(Color.CYAN_BACK, Color.RED_FORE, Long.toString(count));
                                break;
                        }
                    }
                }
                if (i < map.getHeight()) {
                    System.out.println();
                }
            }
        }

        private void printColored(Color color, String out) {
            System.out.print(color + out + defaultStyle);
        }

        private void printColored(Color background, Color foreground, String out) {
            System.out.print(background.toString() + foreground.toString() + out + defaultStyle.toString());
        }

        private void printlnColored(Color color, String out) {
            System.out.println(color + out + defaultStyle);
        }

        private void printlnColored(Color background, Color foreground, String out) {
            System.out.println(background.toString() + foreground.toString() + out + defaultStyle.toString());
        }

    }
}
