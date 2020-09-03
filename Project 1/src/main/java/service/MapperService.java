package service;

import javafx.util.Pair;
import utils.City;
import utils.Mapper;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class MapperService {

    public void mapperService(Mapper cityMap) {

        ClassLoader classLoader = getClass().getClassLoader();
        File citiesFile = new File(Objects.requireNonNull(classLoader.getResource("cities.txt")).getFile());
        File coordinatesFile = new File(Objects.requireNonNull(classLoader.getResource("coordinates.txt")).getFile());

        ArrayList<Pair<String, Integer>> cityNames = readFileByFrontier(citiesFile);
        ArrayList<Point2D> cityCoordinates = readFileByCoordinate(coordinatesFile);

        populateMap(cityMap.getCityMap(), cityNames, cityCoordinates);
    }

    public ArrayList<Pair<String, Integer>> readFileByFrontier(File citiesFile) {

        ArrayList<Pair<String, Integer>> arr = new ArrayList<>();



        return arr;
    }

    public ArrayList<Point2D> readFileByCoordinate(File coordinatesFile) {

        ArrayList<Point2D> arr = new ArrayList<>();



        return arr;
    }

    public void populateMap(Map<String, City> cityMap, ArrayList<Pair<String, Integer>> cityNames, ArrayList<Point2D> cityCoordinates) {

    }
}
