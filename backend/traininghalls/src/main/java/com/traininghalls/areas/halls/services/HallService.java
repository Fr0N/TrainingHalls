package com.traininghalls.areas.halls.services;

import com.traininghalls.areas.halls.entities.Hall;

import java.util.List;

public interface HallService {
    Hall findById(String id);

    List<Hall> getHalls();
}
