package com.airventure.airventureback.authentication.application;
import com.airventure.airventureback.authentication.domain.entity.User;
import com.airventure.airventureback.authentication.domain.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/user/{id}")
    User getOne(@PathVariable Long id) {
        return userService.getOneUser(id);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/user/{id}")
    User edit(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(newUser, id);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/user/{id}")
    void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

