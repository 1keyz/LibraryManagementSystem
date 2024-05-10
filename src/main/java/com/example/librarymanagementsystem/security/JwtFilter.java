package com.example.librarymanagementsystem.security;

import com.example.librarymanagementsystem.advice.exceptions.CustomAuthenticationException;
import com.example.librarymanagementsystem.model.entities.User;
import com.example.librarymanagementsystem.security.jwt.JwtHelper;
import com.example.librarymanagementsystem.security.service.CustomUserDetailService;
import com.example.librarymanagementsystem.services.abstracts.UserService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SignatureException;


@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private JwtHelper jwtHelper;
    private CustomUserDetailService userDetailService;
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        String token;
        String email;
        if (header != null && header.startsWith("Bearer ")){
            token = header.substring(7);

            try {
                email = jwtHelper.extractUser(token);
            }catch (final JwtException ex) {
               throw new CustomAuthenticationException(ex.getMessage());
            }



            User user = userService.getUserByEmail(email);

            UserDetails userDetail = userDetailService.loadUserByUsername(email);

            if (jwtHelper.validateToken(token,userDetail)){
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getEmail(),
                        user.getPassword(),userDetail.getAuthorities());
                auth.setDetails(userDetail);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
}
