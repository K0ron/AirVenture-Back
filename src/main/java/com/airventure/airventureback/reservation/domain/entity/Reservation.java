package com.airventure.airventureback.reservation.domain.entity;

import com.airventure.airventureback.activity.domain.entity.Activity;
import com.airventure.airventureback.authentication.domain.entity.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_reservation", nullable = false)
    private Date detaReservation = new Date();

    @ManyToMany(mappedBy = "reservations")
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(mappedBy = "reservations")
    private Set<User> reservations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDetaReservation() {
        return detaReservation;
    }

    public void setDetaReservation(Date detaReservation) {
        this.detaReservation = detaReservation;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

}
