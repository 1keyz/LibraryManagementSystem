package com.example.librarymanagementsystem.security;

import com.example.librarymanagementsystem.security.service.CustomBasicAuthenticationEntryPoint;
import com.example.librarymanagementsystem.security.service.CustomBearerTokenAccesDeniedHandler;
import com.example.librarymanagementsystem.security.service.CustomBearerTokenAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private JwtFilter jwtFilter;
    private BCryptPasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private CustomBasicAuthenticationEntryPoint entryPoint;
    private CustomBearerTokenAuthenticationEntryPoint tokenEntryPoint;
    private CustomBearerTokenAccesDeniedHandler accesDeniedHandler;

    public SecurityConfig(JwtFilter jwtFilter, BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService, CustomBasicAuthenticationEntryPoint entryPoint, CustomBearerTokenAuthenticationEntryPoint tokenEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.entryPoint = entryPoint;
        this.tokenEntryPoint = tokenEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer :: disable)
                .httpBasic(httpb -> httpb.authenticationEntryPoint(entryPoint))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(tokenEntryPoint).accessDeniedHandler(accesDeniedHandler))
                .authorizeHttpRequests(aut -> aut.requestMatchers("/auth/login","/auth/register").permitAll())
                .authorizeHttpRequests(aut -> aut.requestMatchers("/book/**").hasRole("ADMIN")
                        .requestMatchers("/borrowed-book/**").hasRole("USER"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }




}
