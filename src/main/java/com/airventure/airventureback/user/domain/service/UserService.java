package com.airventure.airventureback.user.domain.service;

import com.airventure.airventureback.user.domain.entity.User;
import com.airventure.airventureback.authentication.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
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
                    user.setPassword(newUser.getPassword());

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
}
