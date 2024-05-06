package com.example.librarymanagementsystem.services.abstracts;

import com.example.librarymanagementsystem.model.entities.Adress;
import com.example.librarymanagementsystem.model.entities.User;

public interface AdressService {
    Adress saveAdress(String city , int postalCode , User user);
}
