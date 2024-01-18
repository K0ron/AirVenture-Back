package com.airventure.airventureback.authentication.domain.service;

import com.airventure.airventureback.authentication.domain.dto.UserDTO;
import com.airventure.airventureback.authentication.domain.entity.User;
import com.airventure.airventureback.authentication.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserMapper userMapper;

    public UserRegisterService(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO UserRegister(User userData) {
        userData.setPassword(generateHashedPassword(userData.getPassword()));
        try {
            return userMapper.transformUserEntityIntoUserDto(userRepository.save(userData), true);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateHashedPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
