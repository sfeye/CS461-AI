package service;

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

        ArrayList<Map<String, Integer>> cityNames = readFileByFrontier(citiesFile);
        ArrayList<Point2D> cityCoordinates = readFileByCoordinate(coordinatesFile);

        populateMap(cityMap.getCityMap(), cityNames, cityCoordinates);
    }

    public ArrayList<Map<String, Integer>> readFileByFrontier(File citiesFile) {

        ArrayList<Map<String, Integer>> arr = new ArrayList<>();



        return arr;
    }

    public ArrayList<Point2D> readFileByCoordinate(File coordinatesFile) {

        ArrayList<Point2D> arr = new ArrayList<>();



        return arr;
    }

    public void populateMap(Map<String, City> cityMap, ArrayList<Map<String, Integer>> cityNames, ArrayList<Point2D> cityCoordinates) {

    }
}
