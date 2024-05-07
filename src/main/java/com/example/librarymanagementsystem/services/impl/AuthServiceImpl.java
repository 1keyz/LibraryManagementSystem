package com.example.librarymanagementsystem.services.impl;

import com.example.librarymanagementsystem.advice.exceptions.BusinessException;
import com.example.librarymanagementsystem.advice.exceptions.LoginException;
import com.example.librarymanagementsystem.dtos.request.LoginRequestDto;
import com.example.librarymanagementsystem.dtos.request.UserRequestDto;
import com.example.librarymanagementsystem.dtos.response.LoginResponseDto;
import com.example.librarymanagementsystem.dtos.response.UserResponseDto;
import com.example.librarymanagementsystem.model.entities.User;
import com.example.librarymanagementsystem.repositories.UserRepository;
import com.example.librarymanagementsystem.security.jwt.JwtHelper;
import com.example.librarymanagementsystem.services.abstracts.AdressService;
import com.example.librarymanagementsystem.services.abstracts.AuthService;
import com.example.librarymanagementsystem.services.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private AdressService adressService;
    private BCryptPasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtHelper jwtHelper;
    @Override
    public UserResponseDto userRegister(UserRequestDto requestDto) {
        User user = UserMapper.INSTANCE.fromUserRequestDtoToUser(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRole(requestDto.getRole());
        userRepository.save(user);
        user.setUserAdress(adressService.saveAdress(requestDto.getCity(), requestDto.getPostalCode(),user));

        UserResponseDto responseDto = modelMapper.map(userRepository.save(user),UserResponseDto.class);
        return responseDto;
    }

    @Override
    public LoginResponseDto userLogin(LoginRequestDto requestDto) {
       try {
           Authentication authentication = authenticationManager
                   .authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(),requestDto.getPassword()));
       }catch (AuthenticationException ex) {
           throw new LoginException("your email or password is wrong chech it !");
       }
        String token = jwtHelper.generateToken(requestDto.getEmail());
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .token(token)
                .build();
        return loginResponseDto;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object email = authentication.getPrincipal();

        if (email !=null){
            return userRepository.getUserByEmail(email.toString());
        }
        throw new BusinessException("Not foun current user");
    }

}
