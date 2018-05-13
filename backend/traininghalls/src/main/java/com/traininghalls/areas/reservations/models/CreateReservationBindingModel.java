package com.traininghalls.areas.reservations.models;

import java.util.Date;

public class CreateReservationBindingModel {

    private String  hallId;

    private String start;

    private String end;

    public CreateReservationBindingModel() {
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
