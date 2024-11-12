/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kolektifhost.simple_dms.service.UsersService;
import com.kolektifhost.simple_dms.utils.JwtFilter;

/**
 *
 * @author najib
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtRequestFilter;
    private final UsersService usersService;

    public SecurityConfig(JwtFilter jwtRequestFilter, UsersService userDetailsService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.usersService = userDetailsService;
    }

    /**
     * The security filter chain for the application. This method is used to configure
     * Spring Security to protect the application's resources.
     * 
     * The configuration is as follows:
     * <ul>
     * <li>CSRF protection is disabled</li>
     * <li>Requests to /auth/** are allowed without authentication</li>
     * <li>Requests to /admin are only allowed for users with the role ADMIN</li>
     * <li>Requests to /api are allowed for users with the roles USER or ADMIN</li>
     * <li>All other requests require authentication</li>
     * <li>The session creation policy is STATELESS, meaning that there is no session
     * created for the user</li>
     * </ul>
     * 
     * The JWT filter is added before the UsernamePasswordAuthenticationFilter in the
     * filter chain. This means that the JWT filter will be executed first, and if the
     * JWT is valid, the filter chain will be skipped and the request will be allowed.
     * If the JWT is invalid, the request will be rejected.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/api").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * This method is used to configure the authentication manager for the
     * application. The authentication manager is responsible for authenticating
     * users and generating an authentication token.
     * 
     * The configuration is as follows:
     * <ul>
     * <li>The user details service is set to the usersService bean</li>
     * <li>The password encoder is set to the passwordEncoder bean</li>
     * </ul>
     * 
     * The authentication manager is then built and returned.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(usersService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    /**
     * Provides a password encoder bean that uses the BCrypt hashing algorithm.
     * This encoder is used to securely hash and verify user passwords.
     * 
     * @return a PasswordEncoder instance configured to use BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
