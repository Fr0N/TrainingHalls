package com.traininghalls.areas.reservations.servicesImpl;

import com.traininghalls.areas.reservations.repositories.ReservationRepository;
import com.traininghalls.areas.reservations.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<String> getFreeHallIdsByDayAndTimePeriod(String reservationDay, String reservationStartHour, String reservationEndHour) {
        return this.reservationRepository.getFreeHallIdsByDayAndTimePeriod(reservationDay, reservationStartHour, reservationEndHour);
    }
}
