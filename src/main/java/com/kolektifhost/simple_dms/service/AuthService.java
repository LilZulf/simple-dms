/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kolektifhost.simple_dms.entity.Users;
import com.kolektifhost.simple_dms.repository.UsersRepository;
import com.kolektifhost.simple_dms.utils.JwtUtils;

/**
 *
 * @author najib
 */
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Logs in a user using the provided username and password. If the
     * credentials are valid, a JWT token is returned that can be used to
     * authenticate the user in subsequent requests.
     *
     * @param username the username of the user to be logged in
     * @param password the password of the user to be logged in
     *
     * @return a JWT token that can be used to authenticate the user
     */
    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final UserDetails userDetails = usersService.loadUserByUsername(username);
        return jwtUtils.generateToken(userDetails.getUsername());
    }

    /**
     * Registers a new user and returns a JWT token that can be used to
     * authenticate the user.
     *
     * @param username the username of the user to be registered
     * @param password the password of the user to be registered
     * @param email    the email of the user to be registered
     *
     * @return a JWT token that can be used to authenticate the user
     *
     * @throws RuntimeException if the user already exists
     */
    public String register(String username, String password, String email) {
        if (usersRepository.findByUsername(username) != null) {
            throw new RuntimeException("User already exist");
        }
        Users user = new Users();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        usersRepository.save(user);
        return jwtUtils.generateToken(user.getUsername());
    }
}
