package utils;

import java.awt.geom.Point2D;

public class City {

    String name;
    Point2D coordinate;
    int frontier;

    public City (String name, Point2D coordinate, int frontier) {
        this.name = name;
        this.coordinate = coordinate;
        this.frontier = frontier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point2D getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    public int getFrontier() {
        return frontier;
    }

    public void setFrontier(int frontier) {
        this.frontier = frontier;
    }
}
