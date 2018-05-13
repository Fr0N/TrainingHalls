package com.traininghalls.areas.reservations.models;

import java.util.Date;

public class CreateReservationBindingModel {

    private String  hallId;

    private String day;

    private Date start;

    private Date end;

    public CreateReservationBindingModel() {
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
