package service;

import org.apache.commons.math3.util.Pair;
import utils.City;
import utils.Mapper;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MapperService {

    public void mapperService(Mapper cityMap) {

        ClassLoader classLoader = getClass().getClassLoader();

        File citiesFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("cities.txt")).getFile());
        File coordinatesFile = new File(Objects.requireNonNull(classLoader.getResource("coordinates.txt")).getFile());

        ArrayList<Pair<String, Integer>> cityNames = new ArrayList<>();
        ArrayList<Pair<String, Point2D.Float>> cityCoordinates = new ArrayList<>();

        try{
            cityNames = readFileByFrontier(citiesFile);
            cityCoordinates = readFileByCoordinate(coordinatesFile);
        } catch(IOException e) {
            System.out.println(e);
        }

        populateMap(cityMap.getCityMap(), cityNames, cityCoordinates);
    }

    public ArrayList<Pair<String, Integer>> readFileByFrontier(File citiesFile) throws IOException {

        ArrayList<Pair<String, Integer>> arr = new ArrayList<>();

        int frontierCount = 0;
        FileReader fr = new FileReader(citiesFile);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line;

        while((line = br.readLine()) != null) {
            frontierCount += 1;
            String[] parseSpace = line.split(" ");

            for (String city: parseSpace)
                arr.add(new Pair<String, Integer>(city, frontierCount));
        }

        return arr;
    }

    public ArrayList<Pair<String, Point2D.Float>> readFileByCoordinate(File coordinatesFile) throws IOException {

        ArrayList<Pair<String, Point2D.Float>> arr = new ArrayList<>();

        FileReader fr = new FileReader(coordinatesFile);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while((line = br.readLine()) != null) {

            String[] parseSpace = line.split(" ");

            arr.add(new Pair<String, Point2D.Float>(parseSpace[0].toUpperCase(),new Point2D.Float(Float.parseFloat(parseSpace[1]), Float.parseFloat(parseSpace[2]))));
        }

        return arr;
    }

    public void populateMap(Map<String, City> cityMap, ArrayList<Pair<String, Integer>> cityNames, ArrayList<Pair<String, Point2D.Float>> cityCoordinates) {

        Point2D.Float defaultPoint = new Point2D.Float(0,0);

        for (Pair<String, Integer> city: cityNames) {

            if(cityMap.containsKey(city.getKey().toUpperCase())) {
                cityMap.get(city.getKey().toUpperCase()).setFrontier(city.getValue());
            } else {
                List<Integer> frontier = new ArrayList<>();
                frontier.add(city.getValue());
                cityMap.put(city.getKey().toUpperCase(), new City(city.getKey().toUpperCase(), defaultPoint, frontier));
            }
        }

        for (Pair<String, Point2D.Float> coordinate: cityCoordinates)
            cityMap.get(coordinate.getKey()).setCoordinate(coordinate.getValue());

        //For file read testing
        cityMap.forEach((key, value) -> System.out.println(key.toString() + ": (" + value.getCoordinate().getX() + ", " +value.getCoordinate().getY() + ") " + value.getFrontier()));
    }
}
