package com.example.librarymanagementsystem.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public interface JwtHelper {

    String generateToken(String email);
    boolean validateToken(String token , UserDetails userDetails);
    Date extractExpiration(String token);
    String extractUser(String token);
    String createToken(String email , Map<String,Object> claim);
    Key getSignKey();

}
