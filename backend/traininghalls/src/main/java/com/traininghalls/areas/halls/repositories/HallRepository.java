package com.traininghalls.areas.halls.repositories;

import com.traininghalls.areas.halls.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, String> {

    @Query(value = "SELECT h.id FROM halls AS h LEFT JOIN (\n" +
            "SELECT * FROM reservations AS r WHERE \n" +
            "(r.`start` <= :reservationStartTime AND :reservationStartTime < r.`end` OR \n" +
            "r.`start` < :reservationEndTime AND :reservationEndTime <= r.`end`) OR\n" +
            "(r.`start` >= :reservationStartTime AND :reservationEndTime >= r.`end`)\n" +
            ") result ON result.hall_id = h.id WHERE result.hall_id IS NULL\n", nativeQuery = true)
    List<String> getFreeHallIdsByDayAndTimePeriod(
            @Param("reservationStartTime") String start,
            @Param("reservationEndTime") String end);
}
