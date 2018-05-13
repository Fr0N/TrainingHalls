package com.traininghalls.areas.halls.controllers;

import com.google.gson.Gson;
import com.traininghalls.areas.halls.entities.Hall;
import com.traininghalls.areas.halls.models.SearchHallModel;
import com.traininghalls.areas.halls.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PostMapping("/api/halls/search")
    public @ResponseBody ResponseEntity<?> searchHalls(@RequestBody SearchHallModel searchHallModel) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date start = format.parse(searchHallModel.getStart());
        Date end = format.parse(searchHallModel.getEnd());
        if (start.after(end)) {
            return new ResponseEntity<>(this.gson.toJson("Start date cannot be after end date!"), HttpStatus.BAD_REQUEST);
        }

        if (searchHallModel.getStart() == null || searchHallModel.getStart().isEmpty() || searchHallModel.getEnd() == null || searchHallModel.getEnd().isEmpty()) {
            return new ResponseEntity<>(this.gson.toJson("Dates cannot be empty!"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.gson.toJson(this.hallService.getFreeHallsByDayAndTimePeriod(searchHallModel)), HttpStatus.OK);
    }
}
