package com.traininghalls.areas.reservations.services;

import com.traininghalls.areas.halls.entities.Hall;
import com.traininghalls.areas.reservations.entities.Reservation;
import com.traininghalls.areas.reservations.models.CreateReservationBindingModel;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<String> getFreeHallIdsByDayAndTimePeriod(String reservationStartTime, String reservationEndTime);

    boolean  checkIfHallIsFreeByDayAndTimePeriod(String hallId, Date reservationStart, Date reservationEnd);

    boolean createReservation(CreateReservationBindingModel reservationBindingModel) throws ParseException;

    List<Reservation> getAllReservationsForHallById(String id);
}
