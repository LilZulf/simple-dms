/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kolektifhost.simple_dms.entity.Users;
import com.kolektifhost.simple_dms.repository.UsersRepository;

/**
 *
 * @author najib
 */
@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all users
     */
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    /**
     * Retrieves a user by their username from the repository.
     *
     * @param username the username to search for
     * @return a UserDetails object with the user's information
     * @throws UsernameNotFoundException if no user is found with the given
     * username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    /**
     * Retrieves a user by their ID from the repository.
     *
     * @param id the ID to search for
     * @return the user with the given ID, or null if no user is found
     */
    public Users getById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    /**
     * Saves a user to the repository.
     *
     * @param user the user to be saved
     * @return the saved user
     */
    public Users saveUsers(Users user) {
        return usersRepository.save(user);
    }

    /**
     * Deletes a user from the repository.
     *
     * @param user the user to be deleted
     * @return the deleted user
     */
    public Users deleteUsers(Users user) {
        usersRepository.delete(user);
        return user;
    }

}
