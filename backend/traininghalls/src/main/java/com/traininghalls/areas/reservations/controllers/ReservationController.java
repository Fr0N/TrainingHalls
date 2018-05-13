package com.traininghalls.areas.reservations.controllers;

import com.google.gson.Gson;
import com.traininghalls.areas.reservations.models.CreateReservationBindingModel;
import com.traininghalls.areas.reservations.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    public @ResponseBody String getFree(@RequestParam(required = true, name = "start_time") String start,
                                        @RequestParam(required = true, name = "end_time") String end) {

        return this.gson.toJson(this.reservationService.getFreeHallIdsByDayAndTimePeriod(start, end));
    }


    //Currently not used
    @GetMapping("/api/reservations")
    public @ResponseBody String getAll(@RequestParam(required = true, name = "hall_id") String hallId) {

        return this.gson.toJson(this.reservationService.getAllReservationsForHallById(hallId));
    }

    @PostMapping("/api/reservation/create")
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationBindingModel reservationBindingModel) throws ParseException {
        boolean result = this.reservationService.createReservation(reservationBindingModel);

        if (result == false) {
            return new ResponseEntity<>("Something went wrong while processing your request...", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("test is ok", HttpStatus.OK);
    }

    @GetMapping(value = "/api/test", produces = "application/json")
    public @ResponseBody String test() {

        return this.gson.toJson("TEST");
    }
}
