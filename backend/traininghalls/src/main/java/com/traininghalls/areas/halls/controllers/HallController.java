package com.traininghalls.areas.halls.controllers;

import com.google.gson.Gson;
import com.traininghalls.areas.halls.entities.Hall;
import com.traininghalls.areas.halls.models.SearchHallModel;
import com.traininghalls.areas.halls.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HallController {

    private final HallService hallService;

    private final Gson gson;

    @Autowired
    public HallController(HallService hallService, Gson gson) {
        this.hallService = hallService;
        this.gson = gson;
    }

    @GetMapping("/api/halls/all")
    public @ResponseBody String getHalls() {
        List<Hall> halls = this.hallService.getHalls();

        return this.gson.toJson(halls);
    }

    @GetMapping("/api/halls/{id}")
    public @ResponseBody String getHalls(@PathVariable(name = "id") String id) {
        return this.gson.toJson(this.hallService.findById(id));
    }

    //Gets all halls (only the ids) that aren't reserved on the given day, for the given time period
    //TODO:Should return models of the halls and ids
//    @GetMapping("/api/reservation/getFree")
//    public @ResponseBody String getFreeHalls(@RequestParam(required = true, name = "start_time") String start,
//                                             @RequestParam(required = true, name = "end_time") String end) {
//
//        return this.gson.toJson(this.hallService.getFreeHallsByDayAndTimePeriod(start, end));
//    }

    @PostMapping("/api/halls/search")
    public @ResponseBody String searchHalls(@RequestBody SearchHallModel searchHallModel) {
        return this.gson.toJson(this.hallService.getFreeHallsByDayAndTimePeriod(searchHallModel));
    }
}
