package service;

import org.apache.commons.collections4.CollectionUtils;
import utils.City;
import utils.Mapper;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DistanceService {

    public void distanceService(Mapper cityMap, String start, String end) {

        List<City> visitedCities = new ArrayList<>();
        List<Integer> visitedFrontiers = new ArrayList<>();
        City startCity = cityMap.getCityMap().get(start);
        City endCity = cityMap.getCityMap().get(end);

        if(startCity == null || endCity == null) {
            System.out.println("The town could not be located, try again.");
            return;
        }
//        System.out.println(startCity.toString());
//        System.out.println(endCity.toString());

        while(!startCity.getName().equalsIgnoreCase(endCity.getName())) {
            visitedCities.add(startCity);
            startCity = getNextCity(cityMap, startCity, endCity, visitedCities, visitedFrontiers);
        }

        System.out.println(showPath(visitedCities));
    }

    public City getNextCity(Mapper cityMap, City currCity, City goalCity, List<City> visitedCities, List<Integer> visitedFrontiers) {

        City nextCity = null;

        for (Map.Entry<String, City> city : cityMap.getCityMap().entrySet()) {

            // Check if we are in same frontier as goal
            if(CollectionUtils.containsAny(currCity.getFrontier(), goalCity.getFrontier())) {
                nextCity = goalCity;
                visitedCities.add(goalCity);
                break;
            }
            //If entry is in same frontier as currCity and not in list of visited city
            if(CollectionUtils.containsAny(city.getValue().getFrontier(), currCity.getFrontier()) &&
                    !visitedCities.contains(city.getValue())) {

                if(nextCity == null) {
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
            getNextCity(cityMap, visitedCities.get(visitedCities.size() - 2), goalCity, visitedCities, visitedFrontiers);
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

    public String showPath(List<City> visitedCities) {
        StringBuilder str = new StringBuilder();

        for (City city : visitedCities) {
            str.append(city.getName()).append(" -> ");
        }

        str.append("Welcome to " + visitedCities.get(visitedCities.size() - 1).getName() + "!");
        return str.toString();
    }
}
