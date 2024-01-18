package com.airventure.airventureback.authentication.infrastructure.repository;

import com.airventure.airventureback.authentication.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
