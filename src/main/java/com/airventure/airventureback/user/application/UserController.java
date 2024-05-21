package com.airventure.airventureback.user.application;
import com.airventure.airventureback.user.domain.dto.UserPasswordChangeDTO;
import com.airventure.airventureback.user.domain.entity.User;
import com.airventure.airventureback.user.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    User getOne(@PathVariable Long id) {
        return userService.getOneUser(id);
    }

    @PutMapping("/user/{id}")
    User edit(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(newUser, id);
    }

    @PutMapping("/password-change/{id}")
    public ResponseEntity<?> editPassword(@RequestBody UserPasswordChangeDTO userPasswordChangeDTO, @PathVariable Long id) {
        try {
            User updatedUser = userService.changePasswordWithAuthentication(userPasswordChangeDTO, id);
            return ResponseEntity.ok(updatedUser);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error:" + e.getMessage());
        }
    }

    @DeleteMapping("/user/{id}")
    void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}

