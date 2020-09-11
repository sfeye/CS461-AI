package service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import utils.City;
import utils.Mapper;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistanceService {

    public List<City>  distanceService(Mapper cityMap, String start, String end) {

        List<City> path = new ArrayList<>();
        List<City> visitedCities = new ArrayList<>();
        List<Integer> visitedFrontiers = new ArrayList<>();
        City startCity = cityMap.getCityMap().get(start);
        City endCity = cityMap.getCityMap().get(end);

        if(startCity == null || endCity == null) {
            System.out.println( "The town could not be located, try again." );
        }
//        System.out.println(startCity.toString());
//        System.out.println(endCity.toString());

        while(!startCity.getName().equalsIgnoreCase(endCity.getName())) {
            path.add(startCity);
            visitedCities.add(startCity);
            startCity = getNextCity(cityMap, startCity, endCity, visitedCities, visitedFrontiers, path);
        }

        return path;
    }

    public City getNextCity(Mapper cityMap, City currCity, City goalCity, List<City> visitedCities, List<Integer> visitedFrontiers, List<City> path) {

        City nextCity = null;

        for (Map.Entry<String, City> city : cityMap.getCityMap().entrySet()) {

            // Check if we are in same frontier as goal
            if(CollectionUtils.containsAny(currCity.getFrontier(), goalCity.getFrontier())) {
                nextCity = goalCity;
                path.add(goalCity);
                break;
            }
            //If entry is in same frontier as currCity and not in list of visited city
            if(CollectionUtils.containsAny(city.getValue().getFrontier(), currCity.getFrontier()) &&
                    !visitedCities.contains(city.getValue())) {

                if(nextCity == null) {
                    if(visitedFrontiers.contains(getCommonFrontier(city.getValue(), currCity))) {
                        path.remove(path.size() - 1);
                    }
                    nextCity = city.getValue();
                }
                //If new city is closer than last city
                else if(!currCity.equals(city.getValue()) && calculateDistanceBetweenCities(goalCity, city.getValue()) <
                        calculateDistanceBetweenCities(goalCity, nextCity) &&
                        !visitedFrontiers.contains(getCommonFrontier(currCity, city.getValue()))) {

                    //For distance testing
                    //System.out.println(city.getValue().getName() + ": " + calculateDistanceBetweenCities(currCity, city.getValue()) + "  <  " +
                    //        nextCity.getName() + ": " + calculateDistanceBetweenCities(currCity, nextCity));

                    nextCity = city.getValue();
                }
            }
        }

        //If there is no new city go back and re-try last visited city
        if(nextCity == null && visitedCities.size() >= 2) {
            nextCity = path.get(path.size() - 2);

            path.remove(path.size() - 1);
            path.remove(nextCity);

        }

        visitedFrontiers.add(getCommonFrontier(currCity, nextCity));
        return nextCity;
    }

    public double calculateDistanceBetweenCities (City currCity, City nextCity) {

        return Point2D.distance(currCity.getCoordinate().getX(), currCity.getCoordinate().getY(),
                nextCity.getCoordinate().getX(), nextCity.getCoordinate().getY());
    }

    public int getCommonFrontier(City prevCity, City nextCity) {
        int frontier = 0;

        for(int i = 0; i < prevCity.getFrontier().size(); i++) {
            for (int k = 0; k < nextCity.getFrontier().size(); k++) {
                if (prevCity.getFrontier().get(i).equals(nextCity.getFrontier().get(k))) {
                    frontier = prevCity.getFrontier().get(i);
                    break;
                }
            }
        }
        return frontier;
    }

    public String showPath(List<City> path) {
        StringBuilder str = new StringBuilder();

        for (City city : path) {
            str.append(city.getName()).append(" -> ");
        }

        str.append("Welcome to " + path.get(path.size() - 1).getName() + "!");
        return str.toString();
    }

    public Map<String, Point2D.Float> showCoordinates( List<City> path ) {

        Map<String, Point2D.Float> map = new HashMap<>();

        path.forEach(city -> {
            map.put(city.getName(), city.getCoordinate());
        });

        return map;
    }

    public String printCoordinatesForJSON(Map<String, Point2D.Float> coords) {
        StringBuilder str = new StringBuilder();
        str.append("{");

        coords.forEach((key, value) -> {
            str.append( "\"" + key + "\": { \"x\": \"" + value.getX() + "\", \"y\": \"" + value.getY() + "\" },");
        });

        str.deleteCharAt(str.length() - 1);

        str.append("}");
        return str.toString();
    }
}
