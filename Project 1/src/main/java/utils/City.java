package utils;

import java.awt.geom.Point2D;
import java.util.List;

public class City {

    String name;
    Point2D coordinate;
    List<Integer> frontier;

    public City (String name, Point2D coordinate, List<Integer>  frontier) {
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

    public List<Integer> getFrontier() {
        return frontier;
    }

    public void setFrontier(List<Integer> frontier) {
        this.frontier = frontier;
    }
}
