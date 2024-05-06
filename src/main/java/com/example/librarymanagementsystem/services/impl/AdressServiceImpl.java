package com.example.librarymanagementsystem.services.impl;

import com.example.librarymanagementsystem.model.entities.Adress;
import com.example.librarymanagementsystem.model.entities.User;
import com.example.librarymanagementsystem.repositories.AdressRepository;
import com.example.librarymanagementsystem.services.abstracts.AdressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdressServiceImpl implements AdressService {
    private AdressRepository repository;
    @Override
    public Adress saveAdress(String city , int postalCode, User user) {
        Adress adress = Adress.builder()
                .city(city)
                .postalCode(postalCode)
                .user(user)
                .build();
        return repository.save(adress);
    }
}
