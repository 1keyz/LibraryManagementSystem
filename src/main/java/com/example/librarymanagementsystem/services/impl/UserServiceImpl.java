package com.example.librarymanagementsystem.services.impl;

import com.example.librarymanagementsystem.core.advice.types.NotFoundException;
import com.example.librarymanagementsystem.model.entities.User;
import com.example.librarymanagementsystem.repositories.UserRepository;
import com.example.librarymanagementsystem.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    @Override
    public User getUserById(long userId) {
        return repository.findById(userId).orElseThrow();
    }

    public void userUpdate(User user){
        repository.save(user);
    }

    public boolean userHasPenaltyAmount(long userId){
        User user = getUserById(userId);
        if (user.getPenaltyAmount() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }
}
