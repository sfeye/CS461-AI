package utils;

import java.awt.geom.Point2D;
import java.util.List;

public class City {

    Point2D coordinate;
    List<Integer> frontier;

    public City (Point2D coordinate, List<Integer>  frontier) {
        this.coordinate = coordinate;
        this.frontier = frontier;
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
