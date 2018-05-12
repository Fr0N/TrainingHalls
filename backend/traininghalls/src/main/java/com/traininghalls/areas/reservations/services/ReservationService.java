package com.traininghalls.areas.reservations.services;

import com.traininghalls.areas.reservations.entities.Reservation;
import com.traininghalls.areas.reservations.models.CreateReservationBindingModel;

import java.util.List;

public interface ReservationService {

    List<String> getFreeHallIdsByDayAndTimePeriod(String reservationDay, String reservationStartHour, String reservationEndHour);

    boolean  checkIfHallIsFreeByDayAndTimePeriod(String hallId, String reservationDay, String reservationStartHour, String reservationEndHour);

    boolean createReservation(CreateReservationBindingModel reservationBindingModel);
}
