package com.traininghalls.areas.halls.controllers;

import com.google.gson.Gson;
import com.traininghalls.areas.halls.entities.Hall;
import com.traininghalls.areas.halls.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
