package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DistanceService;
import service.MapperService;
import utils.Mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/map")
public class MapController {

    private DistanceService distanceService;

    private MapperService mapperService;

    String start;
    String end;

    @PostMapping("/points")
    public void generateMap(@RequestBody Map<String, String> coordinates, HttpServletResponse response, HttpServletRequest request) throws IOException {


        coordinates.forEach((key, value) -> {
            if(key.equalsIgnoreCase("start")) {
                setStart(value.toUpperCase());
            }
            else {
                setEnd(value.toUpperCase());
            }
        });

        Mapper cityMap = new Mapper(new HashMap<>());

        mapperService = new MapperService();
        mapperService.mapperService(cityMap);

        distanceService = new DistanceService();
        response.setContentType("application/json");
        response.getWriter().write(
            distanceService.printCoordinatesForJSON(distanceService.distanceService(cityMap, start, end))
        );

    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}
