package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByEmail(String email);
}
