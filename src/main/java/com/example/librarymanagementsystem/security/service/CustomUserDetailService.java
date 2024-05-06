package com.example.librarymanagementsystem.security.service;

import com.example.librarymanagementsystem.model.entities.User;
import com.example.librarymanagementsystem.model.enums.RoleEnum;
import com.example.librarymanagementsystem.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getUserByEmail(username);
        Set<RoleEnum> set = new HashSet<>();
        set.add(user.getRole());
        UserDetails userDetail = UserDetail.builder()
                .user(user)
                .role(set)
                .build();
        return userDetail;
    }
}
