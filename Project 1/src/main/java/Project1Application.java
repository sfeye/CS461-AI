import service.DistanceService;
import service.MapperService;
import utils.Mapper;

import java.util.HashMap;

public class Project1Application {

    public static void main (String[] args) {
        Mapper cityMap = new Mapper(new HashMap<>());

        MapperService mapperService = new MapperService();
        mapperService.mapperService(cityMap);

        DistanceService distanceService = new DistanceService();
        distanceService.distanceService(cityMap);
    }
}
