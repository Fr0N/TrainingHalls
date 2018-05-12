package com.traininghalls.areas.reservations.servicesImpl;

import com.traininghalls.areas.halls.entities.Hall;
import com.traininghalls.areas.halls.services.HallService;
import com.traininghalls.areas.reservations.entities.Reservation;
import com.traininghalls.areas.reservations.models.CreateReservationBindingModel;
import com.traininghalls.areas.reservations.repositories.ReservationRepository;
import com.traininghalls.areas.reservations.services.ReservationService;
import com.traininghalls.areas.users.entities.User;
import com.traininghalls.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final HallService hallService;

    private final UserService userService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, HallService hallService, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.hallService = hallService;
        this.userService = userService;
    }

    @Override
    public List<String> getFreeHallIdsByDayAndTimePeriod(String reservationDay, String reservationStartHour, String reservationEndHour) {
        return this.reservationRepository.getFreeHallIdsByDayAndTimePeriod(reservationDay, reservationStartHour, reservationEndHour);
    }

    @Override
    public boolean checkIfHallIsFreeByDayAndTimePeriod(String hallId, String reservationDay, String reservationStartHour, String reservationEndHour) {
        return this.reservationRepository.checkIfHallIsFreeByDayAndTimePeriod(hallId, reservationDay, reservationStartHour, reservationEndHour) == null;
    }

    @Override
    public boolean createReservation(CreateReservationBindingModel reservationBindingModel) {
        Hall hallToBeReserved = this.hallService.findById(reservationBindingModel.getHallId());
        if (hallToBeReserved == null) {
            return false;
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userService.findOneByUsername(username);

        Reservation reservation = new Reservation();

        reservation.setUser(user);
        reservation.setHall(hallToBeReserved);
        reservation.setDay(reservationBindingModel.getDay());
        reservation.setStartHour(reservationBindingModel.getStartHour());
        reservation.setEndHour(reservationBindingModel.getEndHour());

        System.out.println("Before transaction");
        this.executeTransactionApproval(reservation, user);
        System.out.println("After transaction");



        return true;
    }

    private void executeTransactionApproval(Reservation reservation, User user){

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(new Callable() {
            @Override
            public String call() throws Exception {
                //TODO time should be random
                Thread.sleep(10000);

                System.out.println(saveReservation(reservation, user));

                System.out.println("in call()");
                return "done";
            }
        });

        executor.shutdown();
    }

    private boolean saveReservation(Reservation reservation, User user) {
        if (this.checkIfHallIsFreeByDayAndTimePeriod(
                reservation.getHall().getId(),
                reservation.getDay(),
                reservation.getStartHour(),
                reservation.getEndHour())) {
            Reservation result = this.reservationRepository.save(reservation);
            if (result == null) {
                return false;
            }

            //TODO user balance should decrease

            return true;
        }

        return false;
    }
}
