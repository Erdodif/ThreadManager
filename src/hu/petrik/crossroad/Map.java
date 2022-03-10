package hu.petrik.crossroad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Map {
    static String defaultStyle = Color.BLACK_BACK.toString() + Color.WHITE_FORE.toString();
    ArrayList<ArrayList<Road>> map;
    List<Car> cars;

    public Map(String fileName) throws IOException {
        cars = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        map = new ArrayList<>();
        while (line != null) {
            ArrayList<Road> roadLine = new ArrayList<>();
            String[] data = line.split(";");
            for (String c : data) {
                switch (c) {
                    case "e":
                        roadLine.add(null);
                        break;
                    case "r":
                        roadLine.add(new Road());
                        break;
                    case "c":
                        roadLine.add(new Crossing());
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
            map.add(roadLine);
            line = br.readLine();
        }
        setRoadConnections();
    }

    private Road getRoad(int x, int y) {
        return map.get(x).get(y);
    }

    private boolean hasLeft(int x, int y) {
        boolean isInner = y - 1 > -1;
        return (isInner && getRoad(x, y - 1) != null) || (!isInner && getRoad(x, map.get(x).size() - 1) != null);
    }

    private boolean hasRight(int x, int y) {
        boolean isInner = y + 1 < map.get(x).size();
        return (isInner && getRoad(x, y + 1) != null) || (!isInner && getRoad(x, 0) != null);
    }

    private boolean hasDown(int x, int y) {
        boolean isInner = x + 1 < map.size();
        return (isInner && getRoad(x + 1, y) != null) || (!isInner && getRoad(0, y) != null);
    }

    private boolean hasUp(int x, int y) {
        boolean isInner = x - 1 > -1;
        return (isInner && map.get(x - 1).get(y) != null) || (!isInner && getRoad(map.size() - 1, y) != null);
    }

    public void setRoadConnections() {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) != null) {
                    getRoad(i, j).setLeft(hasLeft(i, j));
                    getRoad(i, j).setRight(hasRight(i, j));
                    getRoad(i, j).setUp(hasUp(i, j));
                    getRoad(i, j).setDown(hasDown(i, j));
                }
            }
        }
    }

    public void printState() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        StringBuilder boby = new StringBuilder();
        for (Car car : cars) {
            boby.append(car.toString());
        }
        ArrayList<Car> carsTemp = new ArrayList<>(cars);
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == null) {
                    printColored(Color.GREEN_BACK, " ");
                } else {
                    int finalJ = j;
                    int finalI = i;
                    Stream<Car> onRoad = carsTemp.stream()
                            .filter(car -> car.getCoordinateX() == finalI && car.getCoordinateY() == finalJ);
                    long count = onRoad.count();
                    switch ((int) count) {
                        case 0:
                            printColored(Color.WHITE_BACK, Color.BLACK_FORE, getRoad(i, j).toString());
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

    public void addCar(Car car) {
        this.cars.add(car);
    }

    @Override
    public String toString() {
        StringBuilder boby = new StringBuilder();
        for (Car car : cars) {
            boby.append(car.toString());
        }
        StringBuilder mapString = new StringBuilder();
        for (ArrayList<Road> mapLine : map) {
            for (Road road : mapLine) {
                if (road == null) {
                    mapString.append(" ");
                } else {
                    mapString.append(road.toString());
                }
            }
            mapString.append('\n');
        }
        return "Cars: " + boby + " \nMap: \n" + mapString;
    }
}
