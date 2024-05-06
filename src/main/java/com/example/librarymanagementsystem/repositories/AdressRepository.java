package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.model.entities.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress , Long> {
}
