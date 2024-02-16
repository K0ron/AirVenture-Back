package com.airventure.airventureback.activity.infrastructure.repository;

import com.airventure.airventureback.activity.domain.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
