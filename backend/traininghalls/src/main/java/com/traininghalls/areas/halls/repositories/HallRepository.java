package com.traininghalls.areas.halls.repositories;

import com.traininghalls.areas.halls.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, String> {
}
