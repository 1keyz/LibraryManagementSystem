package com.example.librarymanagementsystem.security;

import com.example.librarymanagementsystem.core.advice.types.CustomAuthenticationException;
import com.example.librarymanagementsystem.model.entities.User;
import com.example.librarymanagementsystem.security.jwt.JwtHelper;
import com.example.librarymanagementsystem.security.service.CustomUserDetailService;
import com.example.librarymanagementsystem.services.abstracts.UserService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


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

            email = jwtHelper.extractUser(token);


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
