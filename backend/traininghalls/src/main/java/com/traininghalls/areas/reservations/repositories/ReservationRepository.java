package com.traininghalls.areas.reservations.repositories;

import com.traininghalls.areas.halls.entities.Hall;
import com.traininghalls.areas.reservations.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String>{

    //This query gets all halls(only the ids) that doens't have reservations on the given day, for the given time period and
    // it gets also all halls that doesn't have reservations on the given day.
    @Query(value = "SELECT DISTINCT h.id FROM halls AS h " +
            "LEFT JOIN reservations AS r ON h.id = r.hall_id " +
                "WHERE (r.day = :reservationDay " +
                    "AND NOT " +
                    "(r.start_hour <= :reservationStartHour AND :reservationStartHour < r.end_hour " +
                    "OR r.start_hour < :reservationEndHour AND :reservationEndHour <= r.end_hour)) " +
            "OR r.day IS NULL", nativeQuery = true)
    List<String> getFreeHallIdsByDayAndTimePeriod(
            @Param("reservationDay") String reservationDay,
            @Param("reservationStartHour") String reservationStartHour,
            @Param("reservationEndHour") String reservationEndHour);


    @Query(value = "SELECT * FROM reservations AS r WHERE r.hall_id = :hallId AND " +
            "(r.day = :reservationDay AND " +
            "(r.start_hour <= :reservationStartHour AND :reservationStartHour < r.end_hour OR " +
            "r.start_hour < :reservationEndHour AND :reservationEndHour <= r.end_hour))", nativeQuery = true)
    Reservation checkIfHallIsFreeByDayAndTimePeriod(
            @Param("hallId") String hallId,
            @Param("reservationStart") Date reservationStart,
            @Param("reservationEndH") Date reservationEnd);
}
