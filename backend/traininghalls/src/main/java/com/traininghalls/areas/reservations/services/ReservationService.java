package com.traininghalls.areas.reservations.services;

import java.util.List;

public interface ReservationService {
    List<String> getFreeHallIdsByDayAndTimePeriod(String reservationDay, String reservationStartHour, String reservationEndHour);
}
