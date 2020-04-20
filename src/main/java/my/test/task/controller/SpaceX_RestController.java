package my.test.task.controller;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import my.test.task.service.GetLaunchesService;
import my.test.task.service.GetRocketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/spacex")
public class SpaceX_RestController {
    private GetRocketsService getRocketsService;
    private GetLaunchesService getLaunchesService;

    @Autowired
    public SpaceX_RestController(GetRocketsService getRocketsService, GetLaunchesService getLaunchesService) {
        this.getRocketsService = getRocketsService;
        this.getLaunchesService = getLaunchesService;
    }

    @RequestMapping(value = "/getrockets", method = {RequestMethod.POST, RequestMethod.GET})
    public void getRockets() {
        String rockets = getRocketsService.getAllRockets();

        Gson gson = new Gson();

        List<LinkedTreeMap> list = gson.fromJson(rockets, List.class);

        list.stream()
                .map(x -> x.get("rocket_id"))
                .forEach(System.out::println);
    }

    @RequestMapping(value = "/getlaunches", method = {RequestMethod.POST, RequestMethod.GET})
    public void getLaunches() {
        String launches = getLaunchesService.getAllLaunches();

        Gson gson = new Gson();

        List<LinkedTreeMap> list = gson.fromJson(launches, List.class);

        ArrayList<String> missionNames = list
                .stream()
                .map(mission_name -> (String)mission_name.get("mission_name"))
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<String> launchYears = list
                .stream()
                .map(launch_year -> (String) launch_year.get("launch_year"))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
