import service.DistanceService;
import service.MapperService;
import utils.Mapper;

import java.util.HashMap;
import java.util.Scanner;

public class Project1Application {

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the town you wish to start at and click enter.");
        String startTown = sc.nextLine().toUpperCase();

        System.out.println("Enter the town you wish to end at and click enter.");
        String endTown = sc.nextLine().toUpperCase();

        Mapper cityMap = new Mapper(new HashMap<>());

        MapperService mapperService = new MapperService();
        mapperService.mapperService(cityMap);

        DistanceService distanceService = new DistanceService();
        distanceService.distanceService(cityMap, startTown, endTown);
    }
}
