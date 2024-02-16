package com.airventure.airventureback.reservation.infrastructure.exception;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(long id ) {
        super ("Could not found reservation with id" + id);
    }
}
