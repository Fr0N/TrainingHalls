package com.traininghalls.areas.halls.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String name;

    //price from 00:00 to 09:00
    private Double morningPrice;

    //price from 09:00 to 17:00
    private Double daytimePrice;

    //price from 17:00 to 22:00
    private Double eveningPrice;

    //price from 22:00 to 00:00
    private Double nightPrice;

    public Hall() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMorningPrice() {
        return morningPrice;
    }

    public void setMorningPrice(Double morningPrice) {
        this.morningPrice = morningPrice;
    }

    public Double getDaytimePrice() {
        return daytimePrice;
    }

    public void setDaytimePrice(Double daytimePrice) {
        this.daytimePrice = daytimePrice;
    }

    public Double getEveningPrice() {
        return eveningPrice;
    }

    public void setEveningPrice(Double eveningPrice) {
        this.eveningPrice = eveningPrice;
    }

    public Double getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(Double nightPrice) {
        this.nightPrice = nightPrice;
    }
}
