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
    
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

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
    
    public Users getById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users saveUsers(Users user) {
        return usersRepository.save(user);
    }

    public Users deleteUsers(Users user) {
        usersRepository.delete(user);
        return user;
    }

}
