package com.example.librarymanagementsystem.services.abstracts;

import com.example.librarymanagementsystem.model.entities.User;

import java.util.Optional;

public interface UserService {
    User getUserById(long userId);
    void userUpdate(User user);
    boolean userHasPenaltyAmount(long userId);
    User getUserByEmail(String email);
}
