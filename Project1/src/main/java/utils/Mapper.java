package utils;

import java.util.HashMap;
import java.util.Map;

public class Mapper {

    public Map<String, City> cityMap = new HashMap<String, City>();

    public Mapper(Map<String, City> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, City> getCityMap() {
        return cityMap;
    }
}
