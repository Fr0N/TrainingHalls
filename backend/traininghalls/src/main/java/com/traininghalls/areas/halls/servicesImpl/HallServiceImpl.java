package com.traininghalls.areas.halls.servicesImpl;

import com.traininghalls.areas.halls.entities.Hall;
import com.traininghalls.areas.halls.models.SearchHallModel;
import com.traininghalls.areas.halls.repositories.HallRepository;
import com.traininghalls.areas.halls.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Hall> getHalls() {
        return this.hallRepository.findAll();
    }

    @Override
    public List<Hall> getFreeHallsByDayAndTimePeriod(SearchHallModel searchHallModel) {

//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<String> hallIds = this.hallRepository.getFreeHallIdsByDayAndTimePeriod(searchHallModel.getStart(), searchHallModel.getEnd());
        List<Hall> halls = new ArrayList<>();
        for (String id : hallIds) {
            halls.add(this.findById(id));
        }

        return halls;
    }
}
