package com.airventure.airventureback.authentication.domain.service;

import com.airventure.airventureback.authentication.domain.entity.User;
import com.airventure.airventureback.authentication.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserLoginService(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User login(User user) {
        User userEntity = getUserEntityByEmail(user.getEmail());
        if(!verifyHashedPasswordDuringLogin(user.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException();
        }

        user.setRoles(userEntity.getRoles());
        return user;
    }

    public boolean verifyHashedPasswordDuringLogin(String password, String hashedPassword) {
        return bCryptPasswordEncoder.matches(password, hashedPassword);
    }

    public User getUserEntityByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
