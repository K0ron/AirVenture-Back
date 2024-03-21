package com.airventure.airventureback.user.domain.service;

import com.airventure.airventureback.authentication.domain.service.UserLoginService;
import com.airventure.airventureback.authentication.domain.service.UserRegisterService;
import com.airventure.airventureback.user.domain.dto.UserPasswordChangeDTO;
import com.airventure.airventureback.user.domain.entity.User;
import com.airventure.airventureback.authentication.infrastructure.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserLoginService userLoginService;
    private final UserRegisterService userRegisterService;

    public UserService(UserRepository repository, UserLoginService userLoginService, UserRegisterService userRegisterService) {

        this.repository = repository;
        this.userLoginService = userLoginService;
        this.userRegisterService = userRegisterService;
    }

    public User getOneUser(Long id) {
        return repository.findById(id)
                .orElseThrow(/*() -> new ArticleNotFoundException(id)*/)
                ;
    }

    public User updateUser(User newUser, Long id) {
        return repository.findById(id)
                .map( user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());


          /*          if (!newArticle.getComments().isEmpty()){
                        for (Comment comment: newArticle.getComments()) {
                            comment.setArticle(article);
                            commentRepository.save(comment);
                        }
                    }*/

                    return repository.save(user);
                })
                .orElseThrow(/*() -> new ArticleNotFoundException(id)*/);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }


    public User changePasswordWithAuthentication(UserPasswordChangeDTO userPasswordChangeDTO, Long id) {
        return repository.findById(id)
                .map(user -> {
                    if (!userLoginService.verifyHashedPasswordDuringLogin(userPasswordChangeDTO.getPassword(), user.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
                    }

                    String newPassword = userPasswordChangeDTO.getNewPassword();
                    String hashedPassword = userRegisterService.generateHashedPassword(newPassword);
                    user.setPassword(hashedPassword);
                    return repository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error, password was not changed"));
        }
    }