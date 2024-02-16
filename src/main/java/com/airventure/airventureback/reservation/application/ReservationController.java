package com.airventure.airventureback.reservation.application;

import com.airventure.airventureback.reservation.domain.entity.Reservation;
import com.airventure.airventureback.reservation.domain.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/reservations")
    List<Reservation> readAll() {
        return reservationService.getAllReservations();
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/reservation/{id}")
    Reservation readOne(@PathVariable Long id) {
        return reservationService.getOneReservation(id);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping( "/reservations")
    Reservation create(@RequestBody Reservation newReservation) {
        return reservationService.createReservation(newReservation);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/reservation/{id}")
    Reservation edit(@RequestBody Reservation newReservation, @PathVariable Long id) {
        return reservationService.updateReservation(newReservation, id);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/reservation/{id}")
    void delete(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

}
