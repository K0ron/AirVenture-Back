package com.airventure.airventureback.authentication.domain.service;

import com.airventure.airventureback.user.domain.dto.UserDTO;
import com.airventure.airventureback.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDTO transformUserEntityIntoUserDto(User user, Boolean isForResponse) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setEnabled(true);
        userDTO.setRoles(user.getRoles());
        userDTO.setPassword(isForResponse ? "" : userDTO.getPassword());

        return userDTO;
    }
}
