package com.asPasa.testTask.services;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.User;
import com.asPasa.testTask.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public User persistUser(User user){
        return repository.save(user);
    }

    public User findById(Long id){
        return repository.findById(id).orElseThrow(()->new ApplicationException("User not found with id: "+ id));
    }
    public void deleteUser(Long id){
        repository.deleteById(id);
    }
    public User updateUser(Long id, User nUser){
        User user = findById(id);
        user.setState(nUser);
        return repository.save(user);

    }
    public User createUser(String name, String email){
        return new User(name,email);
    }

}
