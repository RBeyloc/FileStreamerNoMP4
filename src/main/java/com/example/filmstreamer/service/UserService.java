package com.example.filmstreamer.service;

import com.example.filmstreamer.model.User;
import com.example.filmstreamer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<Iterable<User>> getAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    public Optional<User> createUser(User user){
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> findUserById(UUID id){
        return userRepository.findById(id);
    }

    public Optional<User> deleteUserById(UUID id){
        //Find out IF this id-user IS in our DB
        Optional<User> userFound = userRepository.findById(id);
        if(userFound.isPresent()) {
            userRepository.deleteById(id);
            return Optional.of(userFound.get());
        } else {
            return null;
        }
    }

    public Optional<User> updateUser(User user) {
        Optional<User> userFound = userRepository.findById(user.getUserUUID());
        if(userFound.isPresent()) {
            return Optional.of(userRepository.save(user));
        } else {
            return null;
        }
    }

    public Optional<Iterable<User>> findUsersByUserEmail(String userEmail){
        return userRepository.findUsersByUserEmail(userEmail);
    }

    public int count() {
        return (int) userRepository.count();
    }

}