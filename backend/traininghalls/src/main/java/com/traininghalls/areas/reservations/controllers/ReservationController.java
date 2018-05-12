package com.traininghalls.areas.reservations.controllers;

import com.google.gson.Gson;
import com.traininghalls.areas.reservations.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    private final Gson gson;

    @Autowired
    public ReservationController(ReservationService reservationService, Gson gson) {
        this.reservationService = reservationService;
        this.gson = gson;
    }

    //Gets all halls (only the ids) that aren't reserved on the given day, for the given time period
    //TODO:Should return models of the halls and ids
    @GetMapping("/api/reservation/getFree")
    public @ResponseBody String getFree(@RequestParam(required = true, name = "day") String day,
                                        @RequestParam(required = true, name = "start_hour") String startHour,
                                        @RequestParam(required = true, name = "end_hour") String endHour) {

        return this.gson.toJson(this.reservationService.getFreeHallIdsByDayAndTimePeriod(day, startHour, endHour));
    }

    @GetMapping(value = "/api/test", produces = "application/json")
    public @ResponseBody String test() {

        return this.gson.toJson("TEST");
    }
}
