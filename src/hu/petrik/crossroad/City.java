package hu.petrik.crossroad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class City {
    static String defaultStyle = Color.BLACK_BACK.toString() + Color.WHITE_FORE.toString();
    Map map;
    List<Car> cars;

    public City(Map map){
        this.map = map;
        this.cars = new ArrayList<>();
    }

    public City(Map map, ArrayList<Car>cars){
        this.map = map;
        this.cars = cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void printState() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        StringBuilder boby = new StringBuilder();
        for (Car car : cars) {
            boby.append(car.toString());
        }
        ArrayList<Car> carsTemp = new ArrayList<>(cars);
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.getRoad(i,j)== null) {
                    printColored(Color.GREEN_BACK, " ");
                } else {
                    int finalJ = j;
                    int finalI = i;
                    Stream<Car> onRoad = carsTemp.stream()
                            .filter(car -> car.getCoordinateX() == finalI && car.getCoordinateY() == finalJ);
                    long count = onRoad.count();
                    switch ((int) count) {
                        case 0:
                            printColored(Color.WHITE_BACK, Color.BLACK_FORE, map.getRoad(i, j).toString());
                            break;
                        case 1:
                            onRoad = carsTemp.stream()
                                    .filter(car -> car.getCoordinateX() == finalI && car.getCoordinateY() == finalJ);
                            Car toPrint = onRoad.collect(Collectors.toList()).get(0);
                            printColored(Color.WHITE_BACK,Color.BLUE_FORE,toPrint.toString());
                            break;
                        default:
                            printColored(Color.WHITE_BACK,Color.RED_FORE,Long.toString(count));
                            break;
                    }
                }
            }
            System.out.println();
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

    @Override
    public String toString() {
        StringBuilder boby = new StringBuilder();
        for (Car car : cars) {
            boby.append(car.toString());
        }
        return "Cars: " + boby + " \n"+map.toString();
    }
}
