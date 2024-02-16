package com.airventure.airventureback.reservation.infrastructure.repository;

import com.airventure.airventureback.reservation.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {




}
