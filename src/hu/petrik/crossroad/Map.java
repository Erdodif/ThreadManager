package hu.petrik.crossroad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Map {
    ArrayList<ArrayList<Road>> map;
    City.Drawer drawer;

    public Map(String fileName) throws IOException {
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

    private void setRoadConnections() {
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

    public void addDrawer(City.Drawer drawer){
        this.drawer = drawer;
    }

    public synchronized void notifyStateChanged(){
        synchronized (this.drawer){
            this.drawer.notify();
        }
    }

    public Road getRoad(int x, int y) {
        return map.get(x).get(y);
    }

    public boolean hasLeft(int x, int y) {
        boolean isInner = y - 1 > -1;
        return (isInner && getRoad(x, y - 1) != null) || (!isInner && getRoad(x, map.get(x).size() - 1) != null);
    }

    public boolean hasRight(int x, int y) {
        boolean isInner = y + 1 < map.get(x).size();
        return (isInner && getRoad(x, y + 1) != null) || (!isInner && getRoad(x, 0) != null);
    }

    public boolean hasDown(int x, int y) {
        boolean isInner = x + 1 < map.size();
        return (isInner && getRoad(x + 1, y) != null) || (!isInner && getRoad(0, y) != null);
    }

    public boolean hasUp(int x, int y) {
        boolean isInner = x - 1 > -1;
        return (isInner && map.get(x - 1).get(y) != null) || (!isInner && getRoad(map.size() - 1, y) != null);
    }

    public int getHeight(){
        return this.map.size();
    }

    public int getWidth(){
        return this.map.get(0).size();
    }

    @Override
    public String toString() {
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
        return "Map: \n" + mapString;
    }
}
