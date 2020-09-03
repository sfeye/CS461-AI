package service;

import utils.City;
import utils.Mapper;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DistanceService {

    public void distanceService(Mapper cityMap, String start, String end) {

        List<City> visitedCities = new ArrayList<>();
        City startCity = cityMap.getCityMap().get(start);
        City endCity = cityMap.getCityMap().get(end);

        while(startCity != endCity) {
            startCity = getNextCity(cityMap, startCity, visitedCities);
        }

        System.out.println(showPath(visitedCities));
    }

    public City getNextCity(Mapper cityMap, City currCity, List<City> visitedCities) {

        City nextCity = null;

        for (Map.Entry<String, City> city : cityMap.getCityMap().entrySet()) {

            //If entry is in same frontier as currCity and not in list of visited city
            if(city.getValue().getFrontier() == currCity.getFrontier() && !visitedCities.contains(city.getValue())) {

                //If new city is closer than last city
                if(nextCity == null || (calculateDistanceBetweenCities(currCity, city.getValue()) <
                        calculateDistanceBetweenCities(currCity, nextCity))) {
                    nextCity = city.getValue();
                }
            }
        }

        //If there is no new city go back and re-try last visited city
        if(nextCity == null) {
            getNextCity(cityMap, visitedCities.get(visitedCities.size() - 2), visitedCities);
            visitedCities.remove(visitedCities.size() - 1);
        }

        visitedCities.add(nextCity);
        return nextCity;
    }

    public double calculateDistanceBetweenCities (City currCity, City nextCity) {

        return Point2D.distance(currCity.getCoordinate().getX(), currCity.getCoordinate().getY(),
                nextCity.getCoordinate().getX(), nextCity.getCoordinate().getY());
    }

    public String showPath(List<City> visitedCities) {
        String str = "";

        for (City city : visitedCities) {
            str += city.getName() + " -> ";
        }

        str += "goal";
        return str;
    }
}
