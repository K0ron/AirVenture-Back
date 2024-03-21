package com.airventure.airventureback.user.domain.dto;

import com.airventure.airventureback.reservation.domain.entity.Reservation;

import java.util.HashSet;
import java.util.Set;

public class UserBodyDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Reservation> reservations = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
