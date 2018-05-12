package com.traininghalls.areas.halls.servicesImpl;

import com.traininghalls.areas.halls.entities.Hall;
import com.traininghalls.areas.halls.repositories.HallRepository;
import com.traininghalls.areas.halls.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Hall findById(String id) {
        return this.hallRepository.findById(id).orElse(null);
    }
}
