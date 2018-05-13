package com.traininghalls.areas.reservations.services;

import com.traininghalls.areas.reservations.entities.Reservation;
import com.traininghalls.areas.reservations.models.CreateReservationBindingModel;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<String> getFreeHallIdsByDayAndTimePeriod(String reservationDay, String reservationStartHour, String reservationEndHour);

    boolean  checkIfHallIsFreeByDayAndTimePeriod(String hallId, Date reservationStart, Date reservationEnd);

    boolean createReservation(CreateReservationBindingModel reservationBindingModel);
}
