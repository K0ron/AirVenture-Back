package com.airventure.airventureback.reservation.domain.service;


import com.airventure.airventureback.reservation.domain.entity.Reservation;
import com.airventure.airventureback.reservation.infrastructure.exception.ReservationNotFoundException;
import com.airventure.airventureback.reservation.infrastructure.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;


    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getOneReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    public Reservation createReservation(Reservation newReservation) {
        return reservationRepository.save(newReservation);
    }

    public Reservation updateReservation(Reservation newReservation, Long id) {
        return reservationRepository.findById(id)
                .map( reservation -> {
                    reservation.setDetaReservation(newReservation.getDetaReservation());
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}
